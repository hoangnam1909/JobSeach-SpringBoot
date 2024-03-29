package com.nhn.mapper;

import com.nhn.entity.WorkExperience;
import com.nhn.model.request.WorkExperienceRequest;
import org.springframework.stereotype.Component;

@Component
public class WorkExperienceMapper {

    public WorkExperience toEntity(WorkExperienceRequest request) {
        WorkExperience workExperience = new WorkExperience();

        workExperience.setFromDate(request.getFromDate());
        workExperience.setToDate(request.getToDate());
        workExperience.setContent(request.getContent());
        workExperience.setPosition(request.getPosition());

        return workExperience;
    }

}
