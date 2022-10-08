package com.nhn.controllers.candidate;

import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.mapper.ApplyJobMapper;
import com.nhn.model.request.CandidateApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/api/apply-job")
public class CandidateApplyJobController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private ApplyJobMapper applyJobMapper;

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

}
