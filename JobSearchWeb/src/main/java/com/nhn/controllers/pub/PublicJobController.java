package com.nhn.controllers.pub;

import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.entity.Job_;
import com.nhn.entity.User;
import com.nhn.repository.JobRepository;
import com.nhn.specifications.JobSpecification;
import com.nhn.specifications.SpecificationConverter;
import com.nhn.specifications.key.JobEnum;
import com.nhn.specifications.key.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/job")
public class PublicJobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SpecificationConverter specificationConverter;

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<Job> jobOptional = jobRepository.findByIdAndAvailableIsTrue(Integer.parseInt(id));

        if (jobOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Processed", "No job found", "No data"));
        } else {
            Job job = jobOptional.get();
            if (job.isAvailable()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Found job with id = " + id, job));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad Request", String.format("Job with id = %s is unavailable", id), job));
            }
        }
    }

    @GetMapping("/get-list")
    ResponseEntity<RespondObject> getAllList() {

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

        List<Job> foundJobs = jobRepository.findAll(specification, Sort.by(Job_.DATE_PUBLISHED).descending());

        return foundJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Jobs found", foundJobs)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No jobs found", new ArrayList<>())
                );
    }

    @PostMapping("/get")
    ResponseEntity<RespondObject> getAll(@RequestBody(required = false) Map<String, String> params,
                                         @RequestParam(name = "page", defaultValue = "1") String page,
                                         @RequestParam(name = "size", required = false, defaultValue = "5") String size) {

        System.err.println("get jobs" + page);

        if (params != null) {
            if (Integer.parseInt(page) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            JobSpecification specification = specificationConverter.jobSpecification(params);
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by(Job_.DATE_PUBLISHED).descending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            if (foundJobs.getTotalElements() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No job found", new ArrayList<>()));

            if (Integer.parseInt(page) > foundJobs.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad request", "Page number of out range", "Page number = " + page));
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Jobs found", foundJobs));
        } else {
            JobSpecification specification = new JobSpecification();
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by(Job_.DATE_PUBLISHED).descending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            return foundJobs.getContent().size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Jobs found", foundJobs)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("Not found", "No jobs found", new ArrayList<>())
                    );
        }
    }

}
