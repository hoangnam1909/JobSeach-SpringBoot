package com.nhn.service;

import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;

public interface CandidateService {

    Candidate updateCandidate(CandidateRequest candidateRequest);

}
