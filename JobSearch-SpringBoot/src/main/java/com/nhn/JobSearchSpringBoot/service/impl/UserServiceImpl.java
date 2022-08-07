package com.nhn.JobSearchSpringBoot.service.impl;

import com.nhn.JobSearchSpringBoot.model.User;
import com.nhn.JobSearchSpringBoot.model.dto.UserDTO;
import com.nhn.JobSearchSpringBoot.model.mapper.UserMapper;
import com.nhn.JobSearchSpringBoot.repository.UserRepository;
import com.nhn.JobSearchSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getListUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOListResult = new ArrayList<>();

        for (User user : userList){
            userDTOListResult.add(UserMapper.toUserDto(user));
        }

        return userDTOListResult;
    }

}
