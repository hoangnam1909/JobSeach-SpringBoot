package com.nhn.mapper;

import com.nhn.dto.request.SkillRequest;
import com.nhn.entity.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public Skill toEntity(SkillRequest request) {
        Skill skill = new Skill();

        skill.setName(request.getName());
        skill.setLevel(request.getLevel());

        return skill;
    }

}
