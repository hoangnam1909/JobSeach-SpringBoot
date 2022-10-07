package com.nhn.service;

import com.nhn.model.request.QualificationRequest;

import java.util.List;

public interface QualificationService {

    boolean add(List<QualificationRequest> qualificationRequestList);

}
