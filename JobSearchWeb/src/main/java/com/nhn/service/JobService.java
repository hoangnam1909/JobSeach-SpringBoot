package com.nhn.service;

import com.nhn.model.request.JobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.entity.Job;

public interface JobService {

    Job insert(JobRequest request);

    Job update(JobUpdateRequest request);

}
