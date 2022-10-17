package com.nhn.service.impl;

import com.nhn.common.Constant;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.User;
import com.nhn.mapper.ApplyJobMapper;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplyJobServiceImpl implements ApplyJobService {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplyJobMapper applyJobMapper;

    @Override
    public ApplyJob add(String candidateUsername, int jobId) {
        ApplyJob applyJob = applyJobMapper.toEntity(candidateUsername, jobId);

        return applyJobRepository.save(applyJob);
    }

    @Override
    public boolean approve(int applyJobId) {
        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()) {
            ApplyJob applyJobApproved = applyJob.get();
            applyJobApproved.setStatus(Constant.APPLYING_STATUS.APPROVED);
            applyJobRepository.save(applyJobApproved);

            return true;
        }

        return false;
    }

    @Override
    public boolean block(int applyJobId) {
        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()) {
            ApplyJob applyJobApproved = applyJob.get();
            applyJobApproved.setStatus(Constant.APPLYING_STATUS.BLOCKED);
            applyJobRepository.save(applyJobApproved);

            return true;
        }

        return false;
    }

    @Override
    public boolean cancel(String candidateUsername, int applyJobId) {
        User candidateUser = userRepository.findUserByUsername(candidateUsername);
        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (candidateUsername == null || applyJob.isEmpty() || applyJob.get().getCandidateUser().getId() != candidateUser.getId())
            return false;

        ApplyJob applyJobApproved = applyJob.get();
        applyJobApproved.setStatus(Constant.APPLYING_STATUS.CANCELLED);
        applyJobRepository.save(applyJobApproved);

        return true;

    }

}
