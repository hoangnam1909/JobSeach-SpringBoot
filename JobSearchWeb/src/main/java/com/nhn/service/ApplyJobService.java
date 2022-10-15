package com.nhn.service;

import com.nhn.entity.ApplyJob;
import com.nhn.model.request.company.CompanyActionApplyJobRequest;

public interface ApplyJobService {

    ApplyJob add(String candidateUsername, int jobId);

    boolean approve(int applyJobId);

    boolean block(int applyJobId);

    boolean cancel(String candidateUsername, int applyJobId);


}
