package com.nhn.controllers.candidate;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.User;
import com.nhn.mapper.ApplyJobMapper;
import com.nhn.model.request.CandidateApplyJobRequest;
import com.nhn.model.request.candidate.CandidateActionApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    private ApplyJobService applyJobService;

    /*
        lấy ra danh sách ApplyJob của candidate user với candidateUsername truyền vào
    */
    @GetMapping("/{candidate-username}")
    ResponseEntity<RespondObject> getJobApplying(@PathVariable(name = "candidate-username") String candidateUsername) {

        try {
            User candidateUser = userRepository.findUserByUsername(candidateUsername);
            if (candidateUser == null || !candidateUser.getRole().equals(Constant.USER_ROLE.CANDIDATE))
                throw new Exception(String.format("Could not find candidate user with user = '%s'", candidateUsername));

            List<ApplyJob> applyJob = applyJobRepository.findByCandidateUserOrderByCreatedDateDesc(candidateUser);

            if (applyJob.size() != 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", "Applying job of candidate user with id = " + candidateUser, applyJob));
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

    /*
        ứng viên nộp đơn ứng tuyển
    */
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

    /*
        ứng viên huỷ đơn ứng tuyển
    */
    @PutMapping("/cancel")
    ResponseEntity<RespondObject> cancel(@RequestBody @Valid CandidateActionApplyJobRequest request) {

        return applyJobService.cancel(request.getApplyJobId()) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Cancelled", true))
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Apply job cancel failed", false));
    }

}
