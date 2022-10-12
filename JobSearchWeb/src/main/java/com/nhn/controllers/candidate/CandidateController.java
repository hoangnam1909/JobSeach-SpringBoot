package com.nhn.controllers.candidate;

import com.nhn.common.RespondObject;
import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;
import com.nhn.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/api")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    /*
        Ứng viên cập nhật thông tin ứng viên
    */
    @PutMapping("/update")
    ResponseEntity<RespondObject> updateCandidate(@RequestBody @Valid CandidateRequest request) {

        try {
            Candidate candidate = candidateService.updateCandidate(request);

            if (candidate != null)
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Save candidate successfully", candidate));
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Save candidate failed", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Save candidate failed", ex));
        }
    }

}
