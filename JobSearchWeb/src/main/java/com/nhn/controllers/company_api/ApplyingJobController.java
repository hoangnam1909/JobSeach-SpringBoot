package com.nhn.controllers.company_api;

import com.nhn.common.RespondObject;
import com.nhn.dto.request.ApplyingJobGetRequest;
import com.nhn.entity.ApplyingJob;
import com.nhn.mapper.ApplyingJobMapper;
import com.nhn.repository.ApplyingJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/applying-job")
public class ApplyingJobController {

    @Autowired
    private ApplyingJobRepository applyingJobRepository;

    @Autowired
    private ApplyingJobMapper applyingJobMapper;

//    @Autowired
//    private ApplyingJobGetRequestValidator applyingJobGetRequestValidator;

//    @GetMapping("/candidate-user-id/{candidate-user-id}")
//    ResponseEntity<RespondObject> getByCandidateUserId(@PathVariable(name = "candidate-user-id") String candidateUserId) {
//        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdCandidateUserId(Integer.parseInt(candidateUserId));
//
//        if (applyingJobs.isEmpty())
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new RespondObject("Fail", "Candidate user id does not exist", candidateUserId));
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(
//                new RespondObject("Found", String.format("Applying job of candidate with userId = %s found", candidateUserId), applyingJobs));
//    }

    @GetMapping("")
    ResponseEntity<RespondObject> getByJobId(@RequestBody @Valid ApplyingJobGetRequest request) {

        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdJobId(request.getJobId());

        if (applyingJobs.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Fail", "Applying job not found", request.getJobId()));

        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", String.format("Applying job of job with jobId = %s found", request.getJobId()), applyingJobMapper.removeKey(applyingJobs)));
    }

}
