package com.nhn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhn.Util.Util;
import com.nhn.model.UserDTO;
import com.nhn.model.request.AdminUserInsertRequest;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import com.nhn.mapper.UserMapper;
import com.nhn.entity.User;
import com.nhn.model.request.test_request.UserImageRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Cloudinary cloudinary;

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
    public User uploadWithAvatar(UserImageRequest userRequest, MultipartFile file) {
        User user = userMapper.toEntity(userRequest);

        if (!file.isEmpty()) {
            Map r = null;
            try {
                r = this.cloudinary.uploader().upload(file.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert r != null;
            user.setAvatar((String) r.get("secure_url"));
        }

        return userRepository.save(user);
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
