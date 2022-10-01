package com.nhn.service.impl;

import com.nhn.Util.Util;
import com.nhn.common.Constant;
import com.nhn.dto.UserDTO;
import com.nhn.dto.request.AdminUserInsertRequest;
import com.nhn.dto.request.UserUpdateRequest;
import com.nhn.mapper.UserMapper;
import com.nhn.model.Candidate;
import com.nhn.model.Company;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO add(AdminUserInsertRequest req) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = userMapper.toEntity(req);
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(int id, UserUpdateRequest req) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            User updateUser = userMapper.toEntity(req);

            if (!Util.isBCrypt(user.get().getPassword()))
                updateUser.setPassword(user.get().getPassword());

            return userMapper.toDTO(userRepository.save(updateUser));
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.userRepository.findByUsername(username);
        if (users.isEmpty())
            throw new UsernameNotFoundException("User does not exist!!!");

        User user = users.get(0);

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core
                .userdetails.User(user.getUsername(), user.getPassword(), auth);
    }

}