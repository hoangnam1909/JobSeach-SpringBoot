package com.nhn.service;

import com.nhn.model.request.company.ActionApplyJobRequest;

public interface ApplyJobService {

    boolean approve(ActionApplyJobRequest request);

    boolean cancel(ActionApplyJobRequest request);

    boolean delete(ActionApplyJobRequest request);

}
