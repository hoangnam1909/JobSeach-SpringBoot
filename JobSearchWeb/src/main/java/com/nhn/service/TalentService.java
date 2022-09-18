package com.nhn.service;

import com.nhn.dto.request.LanguageRequest;
import com.nhn.dto.request.TalentRequest;
import com.nhn.model.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalentService {

    boolean add(List<TalentRequest> talentRequestList);

}
