package com.nhn.service.impl;

import com.nhn.entity.Candidate;
import com.nhn.entity.User;
import com.nhn.mapper.CandidateMapper;
import com.nhn.model.request.CandidateRequest;
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

        User user = userRepository.findOneByUsernameEqualsIgnoreCase(candidateRequest.getCandidateUsername());
        if (user == null)
            return null;

        Candidate candidate = candidateMapper.toEntity(user.getCandidate(), candidateRequest);
        return candidateRepository.save(candidate);
    }

}
