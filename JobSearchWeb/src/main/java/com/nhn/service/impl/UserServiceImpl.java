package com.nhn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.request.authed_request.UpdateUserRequest;
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
import org.springframework.transaction.annotation.Transactional;
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

//    @Override
//    public UserDTO updateUser(String username, UpdateUserRequest request) {
//        User user = userRepository.findUserByUsername(username);
//
//        if (user != null) {
//            User updateUser = userMapper.toEntityUpdate(user, request);
//
//            if (!Util.isBCrypt(user.getPassword()))
//                updateUser.setPassword(user.getPassword());
//
//            return userMapper.toDTO(userRepository.save(updateUser));
//        }
//
//        return null;
//    }

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
    @Transactional
    public User update(String username, UpdateUserRequest request, MultipartFile file) {
        User userUpdating = userRepository.findUserByUsername(username);

        User user = userMapper.toEntity(userUpdating, request);

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
    public User updateProfileInfo(String username, UpdateUserRequest request) {
        User userUpdating = userRepository.findUserByUsername(username);
        User user = userMapper.toEntity(userUpdating, request);
        return userRepository.save(user);
    }

    @Override
    public User updateAvatar(String username, MultipartFile file) {
        User user = userRepository.findUserByUsername(username);

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
    public boolean changePassword(User user, String currentPassword, String newPassword) {
        if (user == null)
            return false;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean updateResetPassword(String email, String token) {
        try {
            User user = userRepository.findUserByEmail(email);
            user.setResetPasswordToken(token);

            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean resetPassword(String resetPasswordToken, String newPassword) {
        try {
            User user = userRepository.findUserByResetPasswordToken(resetPasswordToken);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(newPassword));

            userRepository.save(user);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
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
