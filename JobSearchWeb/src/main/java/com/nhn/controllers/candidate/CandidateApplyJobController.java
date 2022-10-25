package com.nhn.controllers.candidate;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.User;
import com.nhn.mapper.ApplyJobMapper;
import com.nhn.model.response.CandidateApplyJobResponse;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.ApplyJobService;
import com.nhn.util.JwtUtils;
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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    /*
        lấy ra danh sách ApplyJob của candidate user với candidateUsername truyền vào
    */
    @GetMapping("")
    ResponseEntity<RespondObject> getJobApplying() {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String candidateUsername = jwtUtils.extractUsername(accessToken);

            User candidateUser = userRepository.findUserByUsername(candidateUsername);
            if (candidateUser == null || !candidateUser.getRole().equals(Constant.USER_ROLE.CANDIDATE))
                throw new Exception(String.format("Could not find candidate user with user = '%s'", candidateUsername));

            List<ApplyJob> applyJob = applyJobRepository.findByCandidateUserOrderByCreatedDateDesc(candidateUser);

            if (applyJob.size() != 0) {
                List<CandidateApplyJobResponse> responses = applyJobMapper.toResponseList(applyJob);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", "Applying job of candidate user with id = " + candidateUser.getId(), responses));
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
    @PostMapping("/send/{job-id}")
    ResponseEntity<RespondObject> applyJob(@PathVariable(name = "job-id") int jobId) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String candidateUsername = jwtUtils.extractUsername(accessToken);

            ApplyJob applyJob = applyJobService.add(candidateUsername, jobId);

            if (applyJob != null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Saved", "Applying job saved", applyJobMapper.toCandidateResponse(applyJob)));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad request", "Can not save apply job request", null));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Error", "Error message", ex.getMessage()));
        }
    }

    /*
        ứng viên huỷ đơn ứng tuyển
    */
    @PutMapping("/cancel/{apply-job-id}")
    ResponseEntity<RespondObject> cancel(@PathVariable(name = "apply-job-id") int applyJobId) {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String candidateUsername = jwtUtils.extractUsername(accessToken);
        User candidateUser = userRepository.findUserByUsername(candidateUsername);

        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()) {
            if (applyJob.get().getCandidateUser().getId() != candidateUser.getId())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                        new RespondObject("Forbidden", String.format("Apply job with id = %d is not yours", applyJobId), null));

            return applyJobService.cancel(candidateUsername, applyJobId) ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Cancelled", true))
                    :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Failed", "Cancel apply job failed", false));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Not found", "Could not find apply job with id = " + applyJobId, new ArrayList<>()));
        }
    }

}
