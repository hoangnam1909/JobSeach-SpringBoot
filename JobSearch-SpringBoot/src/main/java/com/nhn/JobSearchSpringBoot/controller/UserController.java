package com.nhn.JobSearchSpringBoot.controller;

import com.nhn.JobSearchSpringBoot.model.RespondObject;
import com.nhn.JobSearchSpringBoot.model.User;
import com.nhn.JobSearchSpringBoot.model.dto.UserDTO;
import com.nhn.JobSearchSpringBoot.repository.UserRepository;
import com.nhn.JobSearchSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @GetMapping("")
//    List<User> getAll() {
//        return userRepository.findAll();
//    }

    @GetMapping("")
    ResponseEntity<RespondObject> getAllUsers() {
        try {
            List<UserDTO> userDTOList = userService.getListUsers();

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("ok", "Get all user successfully", userDTOList)
            );
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("fail", "Fail to get all user", "")
            );
        }
    }

}
