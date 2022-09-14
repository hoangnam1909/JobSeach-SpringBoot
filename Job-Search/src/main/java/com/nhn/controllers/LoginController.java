package com.nhn.controllers;

import com.nhn.dto.UserLoginRequestDTO;
import com.nhn.dto.UserSignUpRequestDTO;
import com.nhn.mapper.UserMapper;
import com.nhn.common.RespondObject;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import com.nhn.service.LoginService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<RespondObject> signUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        // validation


        // dto to entity
        User userSignUp = new User();
        userSignUp = userMapper.toEntity(userSignUpRequestDTO);

        // store entity
        userSignUp = userRepository.save(userSignUp);

        // return
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RespondObject("SUCCESS", "User Sign Up Success", userSignUp));
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/login/success")
    public String success(){
        return "hehehe";
    }

//
//    @PostMapping("/login")
//    public ResponseEntity<RespondObject> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
//        RespondObject respondObject = loginService.login(userLoginRequestDTO);
//
//        // return
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(respondObject);
//    }

}
