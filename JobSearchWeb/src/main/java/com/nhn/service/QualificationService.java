package com.nhn.service;

import com.nhn.dto.request.QualificationRequest;

import java.util.List;

public interface QualificationService {

    boolean add(List<QualificationRequest> qualificationRequestList);

}
