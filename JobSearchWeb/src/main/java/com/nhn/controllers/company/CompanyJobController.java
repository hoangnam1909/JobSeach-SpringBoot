package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.model.request.company.CompanyJobRequest;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.repository.JobRepository;
import com.nhn.service.JobService;
import com.nhn.specifications.JobSpecification;
import com.nhn.specifications.SpecificationConverter;
import com.nhn.specifications.key.JobEnum;
import com.nhn.specifications.key.SearchOperation;
import com.nhn.valid.CompanyUsername;
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
public class CompanyJobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private SpecificationConverter specificationConverter;

    @GetMapping("/get-list")
    ResponseEntity<RespondObject> getAllList() {

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

        List<Job> foundJobs = jobRepository.findAll(specification);

        return foundJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Jobs found", foundJobs)
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "No jobs found", new ArrayList<>())
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

    @GetMapping("/count/{company-username}")
    ResponseEntity<RespondObject> count(@PathVariable(name = "company-username") @Valid @CompanyUsername String companyUsername) {

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.COMPANY_USERNAME, companyUsername, SearchOperation.COMPANY_USERNAME));
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

//        List<Job> job = jobRepository.findAll(specification);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Job found", "Jobs counted", jobRepository.count(specification)));
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<Job> job = jobRepository.findByIdAndAvailableIsTrue(Integer.parseInt(id));
        return job.map(value -> ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Job found", "Job a found with id = " + id, value)
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("Processed", "No jobs found", "No data")
        ));
    }

    @PostMapping("/insert")
    ResponseEntity<RespondObject> insert(@RequestBody @Valid CreateJobRequest request) {

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

    @PutMapping("/update")
    ResponseEntity<RespondObject> update(@RequestBody @Valid JobUpdateRequest request) {

        try {
            Job job = jobService.update(request);
            return job != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Job updated", job)
                    ) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Failed", "Update job failed", null)
                    );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Job update failed", ex.getMessage())
            );
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<RespondObject> delete(@RequestBody @Valid CompanyJobRequest request) {
        boolean deleteCheck = jobService.delete(request.getJobId());

        if (deleteCheck)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job deleted", ""));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Job delete failed", ""));
    }

}
