package com.nhn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.request.admin_request.user.AdminAddUserRequest;
import com.nhn.model.request.admin_request.user.AdminUpdateUserRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public User updateProfileInfo(int userId, AdminUpdateUserRequest request) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            return null;

        User userUpdating = userOptional.get();
        User user = userMapper.toEntity(userUpdating, request);
        if (request.getRawPassword() != null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(request.getRawPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUserAvatar(int userId, MultipartFile file) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            return null;

        User user = userOptional.get();

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
    @Transactional
    public User add(AdminAddUserRequest request) {
        User user = userMapper.toEntity(request, request.getRole());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getRawPassword()));

        return userRepository.save(user);
    }

}
