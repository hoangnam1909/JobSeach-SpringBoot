package com.nhn.service;

import com.nhn.model.request.company.CompanyActionApplyJobRequest;

public interface ApplyJobService {

    boolean approve(int applyJobId);

    boolean block(int applyJobId);

    boolean cancel(int applyJobId);


}
