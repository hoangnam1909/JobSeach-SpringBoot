package com.nhn.mapper;

import com.nhn.entity.Qualification;
import com.nhn.model.request.QualificationRequest;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapper {

    public Qualification toEntity(QualificationRequest request) {
        Qualification qualification = new Qualification();

        qualification.setName(request.getName());

        return qualification;
    }

}
