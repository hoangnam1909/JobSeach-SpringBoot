package com.nhn.controllers.pub;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.JobService;
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

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/job")
public class PublicJobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SpecificationConverter specificationConverter;

    @GetMapping("/get-list")
    ResponseEntity<RespondObject> getAllList() {

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

        List<Job> foundJobs = jobRepository.findAll(specification);

        return foundJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.FOUND).body(
                        new RespondObject("Found", "Jobs found", foundJobs)
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

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            if (foundJobs.getTotalElements() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No job found", new ArrayList<>()));

            if (Integer.parseInt(page) > foundJobs.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad request", "Page number of out range", "Page number = " + page));
            }

            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new RespondObject("Found", "Jobs found", foundJobs));
        } else {
            JobSpecification specification = new JobSpecification();
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            return foundJobs.getContent().size() > 0 ?
                    ResponseEntity.status(HttpStatus.FOUND).body(
                            new RespondObject("Found", "Jobs found", foundJobs)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("Not found", "No jobs found", new ArrayList<>())
                    );
        }
    }

}
