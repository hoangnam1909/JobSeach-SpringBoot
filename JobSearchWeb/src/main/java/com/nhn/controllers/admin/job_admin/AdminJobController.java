package com.nhn.controllers.admin.job_admin;

import com.nhn.common.RespondObject;
import com.nhn.entity.Job;
import com.nhn.model.request.admin_request.job.AdminUpdateJobRequest;
import com.nhn.repository.JobRepository;
import com.nhn.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin/api/job")
public class AdminJobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @GetMapping("/{job-id}")
    ResponseEntity<RespondObject> getById(@PathVariable(name = "job-id") int jobId) {
        jobRepository.flush();
        Optional<Job> job = jobRepository.findById(jobId);

        return job.map(value -> ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject(HttpStatus.OK.name(), "Jobs found", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "Jobs not found", null)));
    }

    @GetMapping("/get-list")
    ResponseEntity<RespondObject> getList() {

        List<Job> foundJobs = jobRepository.findAll();

        return foundJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject(HttpStatus.OK.name(), "Jobs found", foundJobs))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "No jobs found", new ArrayList<>()));
    }

    @PutMapping("/update")
    ResponseEntity<RespondObject> insert(@RequestBody @Valid AdminUpdateJobRequest request) {

        try {
            Job job = jobService.update(request);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject(HttpStatus.OK.name(), "Job saved", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Save failed", ex.getMessage())
            );
        }
    }

    @PutMapping("/enable/{job-id}")
    ResponseEntity<RespondObject> enable(@PathVariable(name = "job-id") int jobId) {

        try {
            Optional<Job> jobOptional = jobRepository.findById(jobId);
            if (jobOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "Not found", null));

            Job job = jobOptional.get();
            job.setAvailable(true);
            jobRepository.save(job);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job enabled", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Enabled job failed", ex.getMessage())
            );
        }
    }

    @PutMapping("/disable/{job-id}")
    ResponseEntity<RespondObject> disable(@PathVariable(name = "job-id") int jobId) {

        try {
            Optional<Job> jobOptional = jobRepository.findById(jobId);
            if (jobOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "Not found", null));

            Job job = jobOptional.get();
            job.setAvailable(false);
            jobRepository.save(job);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job disabled", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Disable job failed", ex.getMessage())
            );
        }
    }

}
