package com.nhn.controllers.company;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private UserRepository userRepository;

    @Autowired
    private ApplyJobService applyJobService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    /*
        Nhà tuyển dụng lấy ra đơn ứng tuyển theo job id
    */
    @GetMapping("/job/{job-id}")
    ResponseEntity<RespondObject> getApplyJobByJobId(@PathVariable(name = "job-id") int jobId) {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);
        User companyUser = userRepository.findUserByUsername(companyUsername);

        Optional<Job> job = jobRepository.findById(jobId);

        if (job.get().getCompanyUser().getId() != companyUser.getId())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new RespondObject("Forbidden", String.format("Job with id = %s is not yours", jobId), null));

        List<ApplyJob> applyJobs = applyJobRepository.findByJobAppliedOrderByCreatedDateDesc(job.get());
        if (applyJobs.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Found", "Apply job found", applyJobs));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Not found", "No application from candidate", applyJobs));
    }

    /*
        Nhà tuyển dụng lấy ra các ứng viên đã ứng tuyển theo job id
    */
    @GetMapping("/job/{job-id}/candidates")
    ResponseEntity<RespondObject> getCandidates(@PathVariable(name = "job-id") int jobId) {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);
        User companyUser = userRepository.findUserByUsername(companyUsername);

        Optional<Job> job = jobRepository.findById(jobId);

        if (job.get().getCompanyUser().getId() != companyUser.getId())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new RespondObject("Forbidden", String.format("Job with id = %s is not yours", jobId), null));

        List<ApplyJob> applyJobs;
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
    }

    /*
        Nhà tuyển dụng xác nhận đơn ứng tuyển
    */
    @PutMapping("/approve/{apply-job-id}")
    ResponseEntity<RespondObject> accept(@PathVariable(name = "apply-job-id") int applyJobId) {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);
        User companyUser = userRepository.findUserByUsername(companyUsername);

        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()) {
            if (applyJob.get().getJobApplied().getCompanyUser().getId() != companyUser.getId())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                        new RespondObject("Forbidden", String.format("Job with id = %s is not yours", applyJob.get().getJobApplied().getId()), null));

            return applyJobService.approve(applyJobId) ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Approved", true))
                    :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Failed", "Approving apply job failed", false));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Not found", "Could not find apply job with id = " + applyJobId, new ArrayList<>()));
        }
    }

    /*
        Nhà tuyển dụng chặn đơn ứng tuyển
    */
    @PutMapping("/block/{apply-job-id}")
    ResponseEntity<RespondObject> block(@PathVariable(name = "apply-job-id") int applyJobId) {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);
        User companyUser = userRepository.findUserByUsername(companyUsername);

        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()) {
            if (applyJob.get().getJobApplied().getCompanyUser().getId() != companyUser.getId())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                        new RespondObject("Forbidden", String.format("Job with id = %s is not yours", applyJob.get().getJobApplied().getId()), null));

            return applyJobService.block(applyJobId) ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Blocked", true))
                    :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Failed", "Blocking apply job failed", false));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Not found", "Could not find apply job with id = " + applyJobId, new ArrayList<>()));
        }
    }

}
