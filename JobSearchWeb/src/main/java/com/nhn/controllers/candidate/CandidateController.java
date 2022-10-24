package com.nhn.controllers.candidate;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;
import com.nhn.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/api")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    @PutMapping(value = "/old/update-candidate-info", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> update(@RequestPart("user") @Valid CandidateRequest request,
                                         @RequestPart("file") MultipartFile file) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String candidateUsername = jwtUtils.extractUsername(accessToken);

            Candidate candidate = candidateService.update(candidateUsername, request, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate info successfully", candidate)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage())
            );
        }
    }

    @PutMapping("/update-candidate-info")
    @Transactional
    ResponseEntity<RespondObject> updateCandidateInfo(@RequestBody @Valid CandidateRequest request) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String candidateUsername = jwtUtils.extractUsername(accessToken);

            Candidate candidate = candidateService.updateInfo(candidateUsername, request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate info successfully", candidate)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage())
            );
        }
    }

    @PutMapping(value = "/update-candidate-cv", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateCandidateCV(@RequestPart("file") MultipartFile file) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String candidateUsername = jwtUtils.extractUsername(accessToken);

            Candidate candidate = candidateService.updateCV(candidateUsername, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate cv successfully", candidate)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage())
            );
        }
    }

}
