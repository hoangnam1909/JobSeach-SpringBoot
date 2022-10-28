package com.nhn.controllers.pub;

import com.nhn.common.RespondObject;
import com.nhn.repository.JobCategoryRepository;
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
@RequestMapping(path = "/public/api/job-category")
public class JobCategoryController {

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAllWithoutJobs() {

        List<JobCategoryRepository.JobCategoryWithoutJobs> jobCategoryWithoutJobs = jobCategoryRepository.findAllBy();
        return jobCategoryWithoutJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Job categories found", jobCategoryWithoutJobs)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "No job categories found", "")
                );
    }

}
