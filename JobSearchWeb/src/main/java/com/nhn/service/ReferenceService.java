package com.nhn.service;

import com.nhn.dto.request.ReferenceRequest;

import java.util.List;

public interface ReferenceService {

    boolean add(List<ReferenceRequest> referenceRequestList);


}
