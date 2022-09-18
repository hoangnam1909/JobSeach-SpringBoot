package com.nhn.service;

import com.nhn.dto.request.LanguageRequest;
import com.nhn.dto.request.SkillRequest;

import java.util.List;

public interface SkillService {

    boolean add(List<SkillRequest> skillRequestList);


}
