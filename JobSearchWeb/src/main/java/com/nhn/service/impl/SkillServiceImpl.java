package com.nhn.service.impl;

import com.nhn.dto.request.SkillRequest;
import com.nhn.mapper.SkillMapper;
import com.nhn.model.Candidate;
import com.nhn.model.Skill;
import com.nhn.model.User;
import com.nhn.repository.SkillRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public boolean add(List<SkillRequest> skillRequestList) {
        User user = userRepository.findOneByUsernameEqualsIgnoreCase(skillRequestList.get(0).getUsername());
        Candidate candidate = user.getCandidate();

        skillRepository.deleteAll(candidate.getSkills());

        for (SkillRequest skillRequest : skillRequestList) {

            Skill skill = skillMapper.toEntity(skillRequest);
            skill.setCandidate(user.getCandidate());

            skillRepository.save(skill);
        }

        User userCheckAfterSaving = userRepository.findOneByUsernameEqualsIgnoreCase(skillRequestList.get(0).getUsername());

        return userCheckAfterSaving.getCandidate().getSkills().size() == skillRequestList.size();
    }
}
