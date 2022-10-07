package com.nhn.service;

import com.nhn.model.request.TalentRequest;

import java.util.List;

public interface TalentService {

    boolean add(List<TalentRequest> talentRequestList);

}
