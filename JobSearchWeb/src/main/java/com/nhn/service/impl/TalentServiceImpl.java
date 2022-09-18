package com.nhn.service.impl;

import com.nhn.dto.request.TalentRequest;
import com.nhn.mapper.TalentMapper;
import com.nhn.model.Candidate;
import com.nhn.model.Talent;
import com.nhn.model.User;
import com.nhn.repository.TalentRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentServiceImpl implements TalentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TalentMapper talentMapper;

    @Autowired
    private TalentRepository talentRepository;

    @Override
    public boolean add(List<TalentRequest> talentRequestList) {

        User user = userRepository.findOneByUsernameEqualsIgnoreCase(talentRequestList.get(0).getUsername());
        Candidate candidate = user.getCandidate();

        talentRepository.deleteAll(candidate.getTalents());

        for (TalentRequest talentRequest : talentRequestList) {

            Talent talent = talentMapper.toEntity(talentRequest);
            talent.setCandidate(user.getCandidate());

            talentRepository.save(talent);
        }

        User userCheckAfterSaving = userRepository.findOneByUsernameEqualsIgnoreCase(talentRequestList.get(0).getUsername());

        return userCheckAfterSaving.getCandidate().getTalents().size() == talentRequestList.size();
    }
}
