package com.nhn.service;

import com.nhn.entity.Job;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;

public interface JobService {

    Job add(CreateJobRequest request);

    Job update(JobUpdateRequest request);

    boolean delete(int jobId);

}
