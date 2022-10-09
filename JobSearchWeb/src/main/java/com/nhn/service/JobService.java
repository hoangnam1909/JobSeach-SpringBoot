package com.nhn.service;

import com.nhn.entity.Job;
import com.nhn.model.request.JobRequest;
import com.nhn.model.request.JobUpdateRequest;

public interface JobService {

    Job add(JobRequest request);

    Job update(JobUpdateRequest request);

    boolean delete(int jobId);

}
