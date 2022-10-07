package com.nhn.service;

import com.nhn.model.request.WorkExperienceRequest;

import java.util.List;

public interface WorkExperienceService {

    boolean add(List<WorkExperienceRequest> workExperienceRequestList);

}
