package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.company.CompanyActionApplyJobRequest;
import com.nhn.model.request.company.CompanyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.JobRepository;
import com.nhn.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private ApplyJobService applyJobService;

    /*
        Nhà tuyển dụng lấy ra đơn ứng tuyển theo job id
    */
    @GetMapping("")
    ResponseEntity<RespondObject> getApplyJobByJobId(@RequestBody @Valid CompanyJobRequest request) {

        Optional<Job> job = jobRepository.findById(request.getJobId());
        List<ApplyJob> applyJobs;

        if (job.isPresent()) {
            applyJobs = applyJobRepository.findByJobAppliedOrderByCreatedDateDesc(job.get());

            if (applyJobs.size() > 0)
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", "Apply job found", applyJobs));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No application from candidate", applyJobs));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", String.format("Get applying job of job with jobId = %d found", request.getJobId()), new ArrayList<>()));
        }
    }

    /*
        Nhà tuyển dụng lấy ra các ứng viên đã ứng tuyển theo job id
    */
    @GetMapping("/{job-id}/candidates")
    ResponseEntity<RespondObject> getCandidates(@PathVariable(name = "job-id") int jobId) {

        Optional<Job> job = jobRepository.findById(jobId);
        List<ApplyJob> applyJobs;

        if (job.isPresent()) {
            applyJobs = applyJobRepository.findByJobAppliedOrderByCreatedDateDesc(job.get());
            List<User> candidateUsers = new ArrayList<>();

            for (ApplyJob applyJob : applyJobs) {
                candidateUsers.add(applyJob.getCandidateUser());
            }

            if (candidateUsers.size() > 0)
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", String.format("Candidate applied to job with id = %d found", jobId), candidateUsers));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No application from candidate", new ArrayList<>()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "jobId", new ArrayList<>()));
        }
    }

    /*
        Nhà tuyển dụng xác nhận đơn ứng tuyển
    */
    @PutMapping("/approve")
    ResponseEntity<RespondObject> accept(@RequestBody @Valid CompanyActionApplyJobRequest request) {

        return applyJobService.approve(request) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Approved", true))
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Apply job approve failed", false));
    }

    /*
        Nhà tuyển dụng chặn đơn ứng tuyển
    */
    @PutMapping("/block")
    ResponseEntity<RespondObject> block(@RequestBody @Valid CompanyActionApplyJobRequest request) {

        return applyJobService.block(request) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Approved", "Apply job blocked", true))
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Apply job block failed", false));
    }

}
