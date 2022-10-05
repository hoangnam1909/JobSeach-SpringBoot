package com.nhn.service.impl;

import com.nhn.dto.request.WorkExperienceRequest;
import com.nhn.mapper.WorkExperienceMapper;
import com.nhn.entity.Candidate;
import com.nhn.entity.User;
import com.nhn.entity.WorkExperience;
import com.nhn.repository.UserRepository;
import com.nhn.repository.WorkExperienceRepository;
import com.nhn.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkExperienceMapper workExperienceMapper;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Override
    public boolean add(List<WorkExperienceRequest> workExperienceRequestList) {

        User user = userRepository.findOneByUsernameEqualsIgnoreCase(workExperienceRequestList.get(0).getUsername());
        Candidate candidate = user.getCandidate();

        workExperienceRepository.deleteAll(candidate.getWorkExperiences());

        for (WorkExperienceRequest workExperienceRequest : workExperienceRequestList) {

            WorkExperience workExperience = workExperienceMapper.toEntity(workExperienceRequest);
            workExperience.setCandidate(user.getCandidate());

            workExperienceRepository.save(workExperience);
        }

        User userCheckAfterSaving = userRepository.findOneByUsernameEqualsIgnoreCase(workExperienceRequestList.get(0).getUsername());

        return userCheckAfterSaving.getCandidate().getWorkExperiences().size() == workExperienceRequestList.size();
    }
}
