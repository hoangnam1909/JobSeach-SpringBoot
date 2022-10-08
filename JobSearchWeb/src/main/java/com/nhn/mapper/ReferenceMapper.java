package com.nhn.mapper;

import com.nhn.entity.Reference;
import com.nhn.model.request.ReferenceRequest;
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
