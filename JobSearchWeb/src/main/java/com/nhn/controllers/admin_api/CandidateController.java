package com.nhn.controllers.admin_api;

import com.nhn.common.RespondObject;
import com.nhn.model.Candidate;
import com.nhn.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/api/v1/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Candidate found", candidates)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "No candidate", "")
                );
    }

}
