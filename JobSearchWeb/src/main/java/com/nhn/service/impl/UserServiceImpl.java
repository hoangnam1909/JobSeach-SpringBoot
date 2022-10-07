package com.nhn.service.impl;

import com.nhn.Util.Util;
import com.nhn.dto.UserDTO;
import com.nhn.dto.request.AdminUserInsertRequest;
import com.nhn.dto.request.authed_request.UpdateUserRequest;
import com.nhn.mapper.UserMapper;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO add(AdminUserInsertRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(String username, UpdateUserRequest request) {
        User user = userRepository.findUserByUsername(username);

        if (user != null) {
            User updateUser = userMapper.toEntityUpdate(user, request);

            if (!Util.isBCrypt(user.getPassword()))
                updateUser.setPassword(user.getPassword());

            return userMapper.toDTO(userRepository.save(updateUser));
        }

        return null;
    }

    @Override
    public User currentUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            return userRepository.findUserByUsername(username);
        } catch (Exception ex) {
            return null;
        }
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
