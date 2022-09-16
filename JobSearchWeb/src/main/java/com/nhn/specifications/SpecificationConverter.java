package com.nhn.specifications;

import com.nhn.dto.SearchCriteria;
import com.nhn.model.User;
import com.nhn.specifications.key.SearchOperation;
import com.nhn.specifications.key.UserEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpecificationConverter {

    public UserSpecification userSpecification(Map<String, String> params){
        UserSpecification specification = new UserSpecification();

        if (params.containsKey(UserEnum.USERNAME)){
            specification.add(new SearchCriteria(UserEnum.USERNAME, params.get(UserEnum.USERNAME), SearchOperation.MATCH));
        }

        if (params.containsKey(UserEnum.EMAIL)){
            specification.add(new SearchCriteria(UserEnum.EMAIL, params.get(UserEnum.EMAIL), SearchOperation.MATCH));
        }

        if (params.containsKey(UserEnum.PHONE)){
            specification.add(new SearchCriteria(UserEnum.PHONE, params.get(UserEnum.PHONE), SearchOperation.MATCH));
        }

        if (params.containsKey(UserEnum.ACTIVE)){
            specification.add(new SearchCriteria(UserEnum.ACTIVE, params.get(UserEnum.ACTIVE), SearchOperation.EQUAL));
        }

        if (params.containsKey(UserEnum.FULL_NAME)){
            specification.add(new SearchCriteria(UserEnum.FULL_NAME, params.get(UserEnum.FULL_NAME), SearchOperation.MATCH));
        }

        if (params.containsKey(UserEnum.GENDER)){
            specification.add(new SearchCriteria(UserEnum.GENDER, params.get(UserEnum.GENDER), SearchOperation.EQUAL));
        }

        if (params.containsKey(UserEnum.ADDRESS)){
            specification.add(new SearchCriteria(UserEnum.ADDRESS, params.get(UserEnum.ADDRESS), SearchOperation.MATCH));
        }

        return specification;
    }

}
