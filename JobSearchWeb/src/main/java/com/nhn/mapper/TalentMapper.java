package com.nhn.mapper;

import com.nhn.dto.request.TalentRequest;
import com.nhn.entity.Talent;
import org.springframework.stereotype.Component;

@Component
public class TalentMapper {

    public Talent toEntity(TalentRequest request) {
        Talent talent = new Talent();

        talent.setContent(request.getContent());

        return talent;
    }

}
