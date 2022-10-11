package com.nhn.service.impl;

import com.nhn.entity.Candidate;
import com.nhn.entity.CompanyIndustry;
import com.nhn.entity.Language;
import com.nhn.entity.User;
import com.nhn.mapper.CandidateMapper;
import com.nhn.model.request.CandidateRequest;
import com.nhn.model.request.LanguageRequest;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.LanguageRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public Candidate updateCandidate(CandidateRequest candidateRequest) {

        User user = userRepository.findUserByUsername(candidateRequest.getCandidateUsername());
        if (user == null)
            return null;

        Candidate candidate = candidateMapper.toEntity(user.getCandidate(), candidateRequest);
        if (candidate == null)
            return null;

        // language
        List<Language> languageList = new ArrayList<>();
        languageRepository.deleteAll(candidate.getLanguages());
        for (LanguageRequest languageRequest : candidateRequest.getLanguageRequests())
            languageList.add(languageRepository.save(new Language(languageRequest.getName(), languageRequest.getDescription())));
        candidate.setLanguages(languageList);

        return candidateRepository.save(candidate);
    }

}
