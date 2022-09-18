package com.nhn.service;

import com.nhn.dto.request.LanguageRequest;
import com.nhn.dto.request.WorkExperienceRequest;

import java.util.List;

public interface WorkExperienceService {

    boolean add(List<WorkExperienceRequest> workExperienceRequestList);

}
