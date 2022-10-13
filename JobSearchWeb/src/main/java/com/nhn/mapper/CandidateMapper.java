package com.nhn.mapper;

import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public Candidate toEntity(Candidate candidate, CandidateRequest request) {

        candidate.setYearsExp(request.getYearsExp());
        candidate.setLinkedin(request.getLinkedin());

        return candidate;
    }

}
