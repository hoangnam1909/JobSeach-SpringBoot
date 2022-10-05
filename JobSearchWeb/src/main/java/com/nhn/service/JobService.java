package com.nhn.service;

import com.nhn.dto.request.JobRequest;
import com.nhn.dto.request.JobUpdateRequest;
import com.nhn.entity.Job;

public interface JobService {

    Job insert(JobRequest request);

    Job update(JobUpdateRequest request);

}
