package com.nhn.service.impl;

import com.nhn.entity.Candidate;
import com.nhn.entity.Language;
import com.nhn.entity.User;
import com.nhn.mapper.LanguageMapper;
import com.nhn.model.request.LanguageRequest;
import com.nhn.repository.LanguageRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public boolean add(List<LanguageRequest> languageRequestList) {

        User user = userRepository.findOneByUsernameEqualsIgnoreCase(languageRequestList.get(0).getUsername());
        Candidate candidate = user.getCandidate();

        languageRepository.deleteAll(candidate.getLanguages());

        for (LanguageRequest languageRequest : languageRequestList) {

            Language language = languageMapper.toEntity(languageRequest);
            language.setCandidate(user.getCandidate());

            languageRepository.save(language);
        }

        User userCheckAfterSaving = userRepository.findOneByUsernameEqualsIgnoreCase(languageRequestList.get(0).getUsername());

        return userCheckAfterSaving.getCandidate().getLanguages().size() == languageRequestList.size();
    }

}
