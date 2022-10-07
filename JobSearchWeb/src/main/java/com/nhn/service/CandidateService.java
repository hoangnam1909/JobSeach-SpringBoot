package com.nhn.service;

import com.nhn.model.request.CandidateRequest;
import com.nhn.entity.Candidate;

public interface CandidateService {

    Candidate updateCandidate(CandidateRequest candidateRequest);

}
