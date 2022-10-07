package com.nhn.controllers;

import com.nhn.common.RespondObject;
import com.nhn.model.request.JobRequest;
import com.nhn.model.request.TestRequest;
import com.nhn.entity.Job;
import com.nhn.repository.CommentRepository;
import com.nhn.service.JobService;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestAPI {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("Fail", "No comment found", userService.currentUser()));
    }

    @GetMapping("/valid-request")
    ResponseEntity<RespondObject> validRequest(@RequestBody @Valid TestRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Testing valid request", request));
    }

    @PostMapping("/insert-job")
    ResponseEntity<RespondObject> insert(@RequestBody JobRequest request) {

        try {
            Job job = jobService.insert(request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job saved", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Save failed", ex.getMessage())
            );
        }
    }

}
