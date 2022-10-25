package com.nhn.mapper;

import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    @Autowired
    private UserRepository userRepository;

    public Candidate toEntity(Candidate candidate, CandidateRequest request) {

        candidate.setYearsExp(request.getYearsExp());
        candidate.setLinkedin(request.getLinkedin());

        return candidate;
    }

}
