package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.entity.JobCategory;
import com.nhn.repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/public/api/job-category")
public class JobCategoryController {

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<JobCategory> jobCategories = jobCategoryRepository.findAll();
        return jobCategories.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Job categories found", jobCategories)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "No job categories found", "")
                );
    }

}
