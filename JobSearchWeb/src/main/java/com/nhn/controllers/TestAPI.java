package com.nhn.controllers;

import com.nhn.common.RespondObject;
import com.nhn.entity.Job;
import com.nhn.entity.Job_;
import com.nhn.entity.Requirement;
import com.nhn.model.request.LoginRequest;
import com.nhn.repository.JobRepository;
import com.nhn.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private RequirementRepository requirementRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/find-requirement-by-id/{job-id}")
    ResponseEntity<RespondObject> findAllByJobId(@PathVariable(name = "job-id") int jobId) {

        List<Requirement> requirement = requirementRepository.findAllByJobId(jobId);

        return requirement.size() != 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Requirements found", requirement))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No requirements found with job id = " + jobId, new ArrayList<>()));
    }

    @GetMapping("/requirements/page/{job-id}")
    ResponseEntity<RespondObject> findAllByJobId(@PathVariable(name = "job-id") int jobId,
                                                 @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                                 @RequestParam(name = "size", required = false, defaultValue = "5") String size) {

        Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("jobId").descending());
        Page<Requirement> requirement = requirementRepository.findAll(paging);

        return requirement.getTotalElements() != 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Requirements found", requirement))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No requirements found with job id = " + jobId, new ArrayList<>()));
    }

    @GetMapping("/specification/jobs")
    ResponseEntity<RespondObject> findAllWithSpecification(@RequestParam(name = "title") String title) {

        Specification<Job> titleLike =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(Job_.TITLE), "%" + title + "%");

        List<Job> jobs = jobRepository.findAll(titleLike);

        return jobs.size() != 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Jobs found", jobs))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No jobs found with title like: " + title, new ArrayList<>()));
    }

    @PostMapping("/validator")
    ResponseEntity<RespondObject> validator(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK", "Username valid true", request.getUsername()));
    }

}
