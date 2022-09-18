package com.nhn.mapper;

import com.nhn.dto.request.CandidateRequest;
import com.nhn.model.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public Candidate toEntity(CandidateRequest req) {
        Candidate candidate = new Candidate();

        candidate.setYearsExp(req.getYearsExp());
        candidate.setLinkedin(req.getLinkedin());
        candidate.setCv(req.getCv());

        return candidate;
    }

}
