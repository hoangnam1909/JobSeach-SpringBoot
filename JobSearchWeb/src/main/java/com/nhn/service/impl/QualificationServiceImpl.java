package com.nhn.service.impl;

import com.nhn.entity.Candidate;
import com.nhn.entity.Qualification;
import com.nhn.entity.User;
import com.nhn.mapper.QualificationMapper;
import com.nhn.model.request.QualificationRequest;
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
