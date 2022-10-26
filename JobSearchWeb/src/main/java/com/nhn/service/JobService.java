package com.nhn.service;

import com.nhn.entity.Job;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.model.request.admin_request.job.AdminUpdateJobRequest;

public interface JobService {

    Job update(AdminUpdateJobRequest request);

    Job add(String companyUsername, CreateJobRequest request);

    Job update(JobUpdateRequest request);

    boolean delete(int jobId);

}
