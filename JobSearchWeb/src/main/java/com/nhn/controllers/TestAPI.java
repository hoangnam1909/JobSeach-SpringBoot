package com.nhn.controllers;

import com.nhn.common.RespondObject;
import com.nhn.entity.Job;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.TestRequest;
import com.nhn.repository.CommentRepository;
import com.nhn.service.JobService;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

//    @PostMapping(value = "/test-image",
//            consumes = {
//                    MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.MULTIPART_FORM_DATA_VALUE
//            })
//    ResponseEntity<RespondObject> testImage(@RequestPart("user") UserImageRequest request,
//                                            @RequestPart("file") MultipartFile file) {
//
//        try {
//            User user = userService.update(request, file);
//
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new RespondObject("Ok", "Save user successfully", user)
//            );
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    new RespondObject("Failed", "Error", ex.getMessage())
//            );
//        }
//    }

    @GetMapping("/valid-request")
    ResponseEntity<RespondObject> validRequest(@RequestBody @Valid TestRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Testing valid request", request));
    }

    @PostMapping("/insert-job")
    ResponseEntity<RespondObject> insert(@RequestBody CreateJobRequest request) {

        try {
            Job job = jobService.add(request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job saved", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Save failed", ex.getMessage())
            );
        }
    }

}
