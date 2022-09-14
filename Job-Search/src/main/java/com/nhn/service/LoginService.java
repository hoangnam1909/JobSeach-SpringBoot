package com.nhn.service;

import com.nhn.dto.UserLoginRequestDTO;
import com.nhn.dto.UserSignUpRequestDTO;
import com.nhn.common.RespondObject;
import com.nhn.mapper.UserMapper;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public RespondObject signUp(UserSignUpRequestDTO userSignUpRequestDTO){
        RespondObject respondObject = new RespondObject();

        // dto to entity
        User userSignUp = new User();
        userSignUp = userMapper.toEntity(userSignUpRequestDTO);

        // store entity
        userSignUp = userRepository.save(userSignUp);

        return new RespondObject("SUCCESS", "User Sign Up Success", userSignUp);
    }

    public RespondObject login(UserLoginRequestDTO userLogin){
        RespondObject respondObject = new RespondObject();

        // validation

        // verify
        User user = userRepository.findOneByUsernameIgnoreCaseAndAndPassword(userLogin.getUsername(), userLogin.getPassword());

        // response
        if (user != null){
            respondObject.setStatus("OK");
            respondObject.setMessage("User logged in");
            respondObject.setData("");
        } else {
            respondObject.setStatus("FAILED");
            respondObject.setMessage("User login failed");
            respondObject.setData("");
        }

        return respondObject;
    }
}
