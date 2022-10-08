package com.nhn.controllers.pub;

import com.nhn.common.RespondObject;
import com.nhn.repository.ApplyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/apply-job")
public class ApplyJobController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getCandidateUserApplyJobByJobId() {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", "msg", applyJobRepository.findAll()));
    }

}
