package com.nhn.controllers.admin.JobAdmin;

import com.nhn.common.RespondObject;
import com.nhn.entity.Job;
import com.nhn.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin/api/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/{job-id}")
    ResponseEntity<RespondObject> getById(@PathVariable(name = "job-id") int jobId) {
        jobRepository.flush();
        Optional<Job> job = jobRepository.findById(jobId);

        if (job.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Candidate found", job.get()));
        else
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Candidate not found", null));
    }

}
