package com.nhn.specifications;

import com.nhn.dto.SearchCriteria;
import com.nhn.specifications.key.JobEnum;
import com.nhn.specifications.key.SearchOperation;
import com.nhn.specifications.key.UserEnum;
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

    public JobSpecification jobSpecification(Map<String, String> params){
        JobSpecification specification = new JobSpecification();

        if (params.containsKey(JobEnum.TITLE)){
            specification.add(new SearchCriteria(JobEnum.TITLE, params.get(JobEnum.TITLE), SearchOperation.MATCH));
        }

        if (params.containsKey(JobEnum.BEGINNING_SALARY)){
            specification.add(new SearchCriteria(JobEnum.BEGINNING_SALARY, params.get(JobEnum.BEGINNING_SALARY), SearchOperation.GREATER_THAN_EQUAL));
        }

        if (params.containsKey(JobEnum.ENDING_SALARY)){
            specification.add(new SearchCriteria(JobEnum.ENDING_SALARY, params.get(JobEnum.ENDING_SALARY), SearchOperation.LESS_THAN_EQUAL));
        }

        if (params.containsKey(JobEnum.CATEGORY_ID)){
            specification.add(new SearchCriteria(JobEnum.CATEGORY_ID, params.get(JobEnum.CATEGORY_ID), SearchOperation.CATEGORY_ID));
        }

        if (params.containsKey(JobEnum.JOB_TYPE_ID)){
            specification.add(new SearchCriteria(JobEnum.JOB_TYPE_ID, params.get(JobEnum.JOB_TYPE_ID), SearchOperation.JOB_TYPE_ID));
        }

        if (params.containsKey(JobEnum.POSITION_ID)){
            specification.add(new SearchCriteria(JobEnum.POSITION_ID, params.get(JobEnum.POSITION_ID), SearchOperation.POSITION_ID));
        }

        return specification;
    }

}
