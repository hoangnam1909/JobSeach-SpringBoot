package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.JobRepository;
import com.nhn.valid.ExistingJobId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/apply-job")
public class CompanyApplyJobController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{job-id}")
    ResponseEntity<RespondObject> getCandidateUserApplyJobByJobId(@PathVariable(name = "job-id") @Valid @ExistingJobId int jobId) {

        Optional<Job> job = jobRepository.findById(jobId);
        List<ApplyJob> applyJobs = applyJobRepository.findAllByJobApp(job.get());

        List<User> candidateUsers = userMapper.applyJobToUserList(applyJobs);

        if (applyJobs.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Fail", "Applying job not found", jobId));

        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", String.format("Applying job of job with jobId = %s found", jobId), candidateUsers));
    }

}
