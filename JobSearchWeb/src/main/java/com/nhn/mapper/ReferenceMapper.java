package com.nhn.mapper;

import com.nhn.dto.request.ReferenceRequest;
import com.nhn.model.Reference;
import org.springframework.stereotype.Component;

@Component
public class ReferenceMapper {

    public Reference toEntity(ReferenceRequest request){
        Reference reference = new Reference();

        reference.setName(request.getName());
        reference.setLink(request.getLink());

        return reference;
    }

}