package com.nhn.service;

import com.nhn.model.request.company.CompanyActionApplyJobRequest;

public interface ApplyJobService {

    boolean approve(CompanyActionApplyJobRequest request);

    boolean cancel(int applyJobId);

    boolean block(CompanyActionApplyJobRequest request);

}
