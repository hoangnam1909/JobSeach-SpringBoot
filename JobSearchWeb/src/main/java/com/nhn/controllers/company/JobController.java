package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.mapper.JobMapper;
import com.nhn.model.request.JobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.model.request.company.DeleteJobRequest;
import com.nhn.repository.JobRepository;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private SpecificationConverter specificationConverter;

    @Autowired
    private JobMapper jobMapper;

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
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "No job found", new ArrayList<>()));

            if (Integer.parseInt(page) > foundJobs.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Page number of out range", ""));
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Jobs found", foundJobs));
        } else {
            JobSpecification specification = new JobSpecification();
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            return foundJobs.getContent().size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("OK", "Jobs found", foundJobs)
                    ) :
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "No jobs found", new ArrayList<>())
                    );
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<Job> job = jobRepository.findById(Integer.parseInt(id));
        return job.map(value -> ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Job found", "Job a found with id = " + id, value)
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("Processed", "No jobs found", "No data")
        ));
    }

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody @Valid JobRequest request) {

        try {
            Job job = jobMapper.toEntity(request);
            Job jobSaved = jobRepository.save(job);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job saved", jobSaved));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Save failed", ex.getMessage())
            );
        }
    }

    @PutMapping("")
    ResponseEntity<RespondObject> update(@RequestBody JobUpdateRequest request) {

        try {
            Job job = jobService.update(request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job updated", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Job update failed", ex.getMessage())
            );
        }
    }

    @DeleteMapping("")
    ResponseEntity<RespondObject> delete(@RequestBody @Valid DeleteJobRequest request) {
        boolean deleteCheck = jobService.delete(request.getJobId());

        if (deleteCheck)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job deleted", ""));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Job delete failed", "")
            );
    }

}
