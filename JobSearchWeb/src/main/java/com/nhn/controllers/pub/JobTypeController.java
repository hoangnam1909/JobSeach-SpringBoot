package com.nhn.controllers.pub;

import com.nhn.common.RespondObject;
import com.nhn.entity.JobType;
import com.nhn.repository.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/job-type")
public class JobTypeController {

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<JobType> jobTypes = jobTypeRepository.findAll();
        return jobTypes.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Job types found", jobTypes)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "No job types found", "")
                );
    }

}
