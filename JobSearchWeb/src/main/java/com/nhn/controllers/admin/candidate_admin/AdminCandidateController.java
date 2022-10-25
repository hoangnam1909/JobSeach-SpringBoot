package com.nhn.controllers.admin.candidate_admin;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.Candidate;
import com.nhn.entity.User;
import com.nhn.model.request.CandidateRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.CandidateService;
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
@RequestMapping("/admin/api/candidate")
public class AdminCandidateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateService candidateService;

    @PutMapping(value = "/update/{candidate-user-id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateCandidate(@PathVariable(name = "candidate-user-id") int candidateUserId,
                                                  @RequestPart("user") @Valid CandidateRequest request,
                                                  @RequestPart("file") MultipartFile file) {

        try {
            User user = userRepository.findUserByIdAndRole(candidateUserId, Constant.USER_ROLE.CANDIDATE);
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No candidate found", ""));

            Candidate candidate = candidateService.update(user.getUsername(), request, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate info successfully", candidate));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

}
