package com.nhn.service.impl;

import com.nhn.common.Constant;
import com.nhn.entity.ApplyJob;
import com.nhn.model.request.company.CompanyActionApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplyJobServiceImpl implements ApplyJobService {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Override
    public boolean approve(int applyJobId) {
        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()){
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

        if (applyJob.isPresent()){
            ApplyJob applyJobApproved = applyJob.get();
            applyJobApproved.setStatus(Constant.APPLYING_STATUS.BLOCKED);
            applyJobRepository.save(applyJobApproved);

            return true;
        }

        return false;
    }

    @Override
    public boolean cancel(int applyJobId) {
        Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);

        if (applyJob.isPresent()){
            ApplyJob applyJobApproved = applyJob.get();
            applyJobApproved.setStatus(Constant.APPLYING_STATUS.CANCELLED);
            applyJobRepository.save(applyJobApproved);

            return true;
        }

        return false;
    }

}
