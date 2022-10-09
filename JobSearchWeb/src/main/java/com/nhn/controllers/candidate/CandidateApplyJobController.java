package com.nhn.controllers.candidate;

import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.User;
import com.nhn.mapper.ApplyJobMapper;
import com.nhn.mapper.JobMapper;
import com.nhn.model.request.CandidateApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.ExistingCandidateUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/api/apply-job")
public class CandidateApplyJobController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplyJobMapper applyJobMapper;

    @Autowired
    private JobMapper jobMapper;

    @GetMapping("")
    ResponseEntity<RespondObject> getJobApplying(@RequestParam(name = "candidate-user-id")
                                                 @Valid
                                                 @ExistingCandidateUserId int candidateUserId) {

        try {
            Optional<User> candidateUser = userRepository.findById(candidateUserId);

            List<ApplyJob> applyJob = applyJobRepository.findByCandidateUserOrderByCreatedDateDesc(candidateUser.get());

            if (applyJob.size() != 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Saved", "Applying job of candidate user with id = " + candidateUser, jobMapper.applyJobToJobList(applyJob)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Found", "Do not found", ""));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

    @PostMapping("")
    ResponseEntity<RespondObject> applyJob(@RequestBody @Valid CandidateApplyJobRequest request) {

        try {
            ApplyJob applyJob = applyJobMapper.toEntity(request);
            if (applyJob != null) {
                ApplyJob applyJobSaving = applyJobRepository.save(applyJob);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Saved", "Applying job saved", applyJobSaving));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Found", "Do not found", ""));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Applying job failed", ex.getMessage()));
        }
    }

    @DeleteMapping
    ResponseEntity<RespondObject> deleteApplyJob(@RequestBody @Valid int applyJobId) {

        try {
            Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);
            if (applyJob.isPresent()) {
                applyJobRepository.delete(applyJob.get());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Deleted", "Applying job deleted", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Found", "Do not found", "Do not found apply job with id = " + applyJobId));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Applying job failed", ex.getMessage()));
        }
    }

}
