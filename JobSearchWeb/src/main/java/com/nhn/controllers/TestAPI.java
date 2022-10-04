package com.nhn.controllers;

import com.nhn.common.RespondObject;
import com.nhn.dto.request.JobRequest;
import com.nhn.model.Job;
import com.nhn.repository.CommentRepository;
import com.nhn.service.JobService;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test-array")
    ResponseEntity<RespondObject> testArray() {
        List<Integer> currentTags = new ArrayList<>();
        currentTags.add(1);
        currentTags.add(4);
        currentTags.add(5);
        currentTags.add(7);

        List<Integer> newTags = new ArrayList<>();
        currentTags.add(1);
        currentTags.add(5);
        currentTags.add(3);
        currentTags.add(8);

        List<Integer> addingTags = currentTags;
        addingTags.removeAll(newTags);

        for (Integer addingTag : addingTags) {
            System.err.println(addingTag);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Fail", "No comment found", addingTags));
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
