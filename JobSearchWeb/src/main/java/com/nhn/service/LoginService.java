package com.nhn.service;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.dto.LoginRequest;
import com.nhn.dto.UserSignUpRequestDTO;
import com.nhn.mapper.UserMapper;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

//    public RespondObject signUp(UserSignUpRequestDTO userSignUpRequestDTO) {
//        RespondObject respondObject = new RespondObject();
//
//        // dto to entity
//        User userSignUp = new User();
//        userSignUp = userMapper.toEntity(userSignUpRequestDTO);
//
//        // store entity
//        userSignUp = userRepository.save(userSignUp);
//
//        // generate token
//        String token = jwtUtils.generateJwt(userSignUp);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("accessToken", token);
//
//        respondObject.setData(data);
//
//        // return
//        return respondObject;
//    }

    public RespondObject login(LoginRequest userLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        RespondObject respondObject = new RespondObject();

        // validation

        // verify
        User user = userRepository.findOneByUsernameEqualsIgnoreCase(userLogin.getUsername());
        if (!passwordEncoder.matches(userLogin.getPassword(), user.getPassword()))
            user = null;

        // response
        if (user == null) {
            respondObject.setStatus("Failed");
            respondObject.setMessage("User login failed");
            respondObject.setData("");

            return respondObject;
        }

        // logged in
        if (user.getUsername().equals(userLogin.getUsername()) &&
                passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            respondObject.setStatus("Ok");
            respondObject.setMessage("User logged in");

            respondObject.setData(userLogin);

            // return
            return respondObject;
        }

        return new RespondObject("Failed", "Login failed", "");
    }
}
