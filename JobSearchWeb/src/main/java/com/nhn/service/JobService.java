package com.nhn.service;

import com.nhn.entity.Job;
import com.nhn.model.request.JobRequest;
import com.nhn.model.request.JobUpdateRequest;

public interface JobService {

    Job insert(JobRequest request);

    Job update(JobUpdateRequest request);

}
