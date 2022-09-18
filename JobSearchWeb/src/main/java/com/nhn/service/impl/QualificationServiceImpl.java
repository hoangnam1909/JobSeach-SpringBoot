package com.nhn.service.impl;

import com.nhn.dto.request.QualificationRequest;
import com.nhn.mapper.QualificationMapper;
import com.nhn.model.Candidate;
import com.nhn.model.Qualification;
import com.nhn.model.User;
import com.nhn.repository.QualificationRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QualificationMapper qualificationMapper;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public boolean add(List<QualificationRequest> qualificationRequestList) {
        User user = userRepository.findOneByUsernameEqualsIgnoreCase(qualificationRequestList.get(0).getUsername());
        Candidate candidate = user.getCandidate();

        qualificationRepository.deleteAll(candidate.getQualifications());

        for (QualificationRequest qualificationRequest : qualificationRequestList) {

            Qualification qualification = qualificationMapper.toEntity(qualificationRequest);
            qualification.setCandidate(user.getCandidate());

            qualificationRepository.save(qualification);
        }

        User userCheckAfterSaving = userRepository.findOneByUsernameEqualsIgnoreCase(qualificationRequestList.get(0).getUsername());

        return userCheckAfterSaving.getCandidate().getQualifications().size() == qualificationRequestList.size();
    }

}
