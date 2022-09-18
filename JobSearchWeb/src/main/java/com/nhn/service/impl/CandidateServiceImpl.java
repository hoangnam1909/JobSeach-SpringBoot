package com.nhn.service.impl;

import com.nhn.dto.request.CandidateRequest;
import com.nhn.mapper.CandidateMapper;
import com.nhn.model.Candidate;
import com.nhn.model.User;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public Candidate updateCandidate(CandidateRequest candidateRequest) {

        Candidate candidate = candidateMapper.toEntity(candidateRequest);

        User user = userRepository.findOneByUsernameEqualsIgnoreCase(candidateRequest.getUsername());
        if (user == null)
            return null;

        int candidateId = user.getCandidate().getId();
        candidate.setId(candidateId);

        return candidateRepository.save(candidate);
    }

}
