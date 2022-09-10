package com.nhn.service.impl;

import com.nhn.models.User;
import com.nhn.models.dto.UserDTO;
import com.nhn.models.mapper.UserMapper;
import com.nhn.models.request.UpdateUserReq;
import com.nhn.repository.UserRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO updateUser(int id, UpdateUserReq req) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            User updateUser = userMapper.toEntity(req);
            updateUser.setPassword(user.get().getPassword());
            return UserMapper.toDTO(userRepository.save(updateUser));
        }

        return null;
    }

}
