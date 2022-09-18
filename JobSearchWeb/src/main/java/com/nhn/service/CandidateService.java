package com.nhn.service;

import com.nhn.dto.request.CandidateRequest;
import com.nhn.model.Candidate;

public interface CandidateService {

    Candidate updateCandidate(CandidateRequest candidateRequest);

}
