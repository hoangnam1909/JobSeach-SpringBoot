package com.nhn.mapper;

import com.nhn.model.request.TalentRequest;
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
