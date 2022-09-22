package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.dto.request.JobRequest;
import com.nhn.mapper.JobMapper;
import com.nhn.model.Job;
import com.nhn.repository.JobRepository;
import com.nhn.specifications.JobSpecification;
import com.nhn.specifications.SpecificationConverter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/public/api/v1/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SpecificationConverter specificationConverter;

    @Autowired
    private JobMapper jobMapper;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll(@RequestBody(required = false) Map<String, String> params,
                                         @RequestParam(name = "page", defaultValue = "1") String page) {

        if (params != null) {
            if (Integer.parseInt(page) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            JobSpecification specification = specificationConverter.jobSpecification(params);
            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, 5, Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            if (Integer.parseInt(page) > foundJobs.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Jobs found", foundJobs));
        } else {
            List<Job> foundJobs = jobRepository.findAll();
            return foundJobs.size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("OK", "Jobs found", foundJobs)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("FAIL", "No jobs found", "")
                    );
        }
    }

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody JobRequest request) {

        try {
            Job job = jobRepository.save(jobMapper.toEntity(request));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job saved", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Jobs found", ex.getMessage())
            );
        }

    }

}
