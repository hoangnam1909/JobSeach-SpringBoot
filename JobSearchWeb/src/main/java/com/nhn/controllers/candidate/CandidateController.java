package com.nhn.controllers.candidate;

import com.nhn.common.RespondObject;
import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;
import com.nhn.service.CandidateService;
import com.nhn.valid.RegisteredUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/api")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PutMapping(value = "/update-candidate-info", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> update(@RequestPart("user") @Valid CandidateRequest request,
                                         @RequestPart("file") MultipartFile file) {

        try {
            Candidate candidate = candidateService.update(request, file);

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

    /*
        Ứng viên cập nhật thông tin ứng viên
    */
//    @PutMapping("/update")
//    ResponseEntity<RespondObject> updateCandidate(@RequestBody @Valid CandidateRequest request) {
//
//        try {
//            Candidate candidate = candidateService.update(request);
//
//            if (candidate != null)
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new RespondObject("OK", "Save candidate successfully", candidate));
//            else
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                        new RespondObject("Failed", "Save candidate failed", ""));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    new RespondObject("Failed", "Save candidate failed", ex));
//        }
//    }

}
