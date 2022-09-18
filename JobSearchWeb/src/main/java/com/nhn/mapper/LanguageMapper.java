package com.nhn.mapper;

import com.nhn.dto.request.LanguageRequest;
import com.nhn.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public Language toEntity(LanguageRequest request) {
        Language language = new Language();

        language.setName(request.getName());
        language.setDescription(request.getDescription());

        return language;
    }

}
