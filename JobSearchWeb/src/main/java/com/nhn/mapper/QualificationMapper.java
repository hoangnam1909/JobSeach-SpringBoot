package com.nhn.mapper;

import com.nhn.dto.request.QualificationRequest;
import com.nhn.model.Qualification;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapper {

    public Qualification toEntity(QualificationRequest request) {
        Qualification qualification = new Qualification();

        qualification.setName(request.getName());

        return qualification;
    }

}
