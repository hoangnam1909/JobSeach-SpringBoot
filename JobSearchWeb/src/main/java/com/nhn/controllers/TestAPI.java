package com.nhn.controllers;

import com.nhn.common.RespondObject;
import com.nhn.repository.CommentRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAPI {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("Fail", "No comment found", userService.currentUser()));
    }

}
