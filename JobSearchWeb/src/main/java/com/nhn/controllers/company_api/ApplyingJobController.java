package com.nhn.controllers.company_api;

import com.nhn.common.RespondObject;
import com.nhn.dto.request.IdRequest;
import com.nhn.mapper.ApplyingJobMapper;
import com.nhn.model.ApplyingJob;
import com.nhn.repository.ApplyingJobRepository;
import com.nhn.validator.JobIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private JobIdValidator jobIdValidator;

    @GetMapping("/candidate-user-id/{candidate-user-id}")
    ResponseEntity<RespondObject> getByCandidateUserId(@PathVariable(name = "candidate-user-id") String candidateUserId) {
        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdCandidateUserId(Integer.parseInt(candidateUserId));

        if (applyingJobs.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Candidate user id does not exist", candidateUserId));

        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", String.format("Applying job of candidate with userId = %s found", candidateUserId), applyingJobs));
    }

    @GetMapping("/job-id")
    ResponseEntity<RespondObject> getByJobId(@Valid @RequestBody IdRequest request,
                                             BindingResult result) {

        jobIdValidator.validate(request, result);
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RespondObject("Fail", "Job id invalid", result.getAllErrors()));
        }

        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdJobId(request.getId());

        if (applyingJobs.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Job id does not exist", request.getId()));

        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", String.format("Applying job of job with jobId = %s found", request.getId()), applyingJobMapper.removeKey(applyingJobs)));
    }

}
