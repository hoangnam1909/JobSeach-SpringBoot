package com.nhn.service;

import com.nhn.entity.Candidate;
import com.nhn.model.request.CandidateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface CandidateService {

    Candidate updateInfo(String username, CandidateRequest request);

    Candidate updateCV(String username, MultipartFile file);

    Candidate update(String username, CandidateRequest request, MultipartFile file);

}
