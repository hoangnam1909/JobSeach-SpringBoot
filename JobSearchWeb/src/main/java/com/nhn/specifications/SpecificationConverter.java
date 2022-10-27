package com.nhn.specifications;

import com.nhn.common.SearchCriteria;
import com.nhn.entity.Candidate_;
import com.nhn.entity.User_;
import com.nhn.specifications.key.JobEnum;
import com.nhn.specifications.key.SearchOperation;
import com.nhn.specifications.key.UserEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpecificationConverter {

    public UserSpecification userSpecification(Map<String, String> params) {
        UserSpecification specification = new UserSpecification();

        if (params.containsKey(User_.USERNAME)) {
            specification.add(new SearchCriteria(User_.USERNAME, params.get(User_.USERNAME), SearchOperation.MATCH));
        }

        if (params.containsKey(User_.EMAIL)) {
            specification.add(new SearchCriteria(User_.EMAIL, params.get(User_.EMAIL), SearchOperation.MATCH));
        }

        if (params.containsKey(User_.PHONE)) {
            specification.add(new SearchCriteria(User_.PHONE, params.get(User_.PHONE), SearchOperation.MATCH));
        }

        if (params.containsKey(User_.ACTIVE)) {
            specification.add(new SearchCriteria(User_.ACTIVE, params.get(User_.ACTIVE), SearchOperation.EQUAL));
        }

        if (params.containsKey(User_.FULL_NAME)) {
            specification.add(new SearchCriteria(User_.FULL_NAME, params.get(User_.FULL_NAME), SearchOperation.MATCH));
        }

        if (params.containsKey(User_.GENDER)) {
            specification.add(new SearchCriteria(User_.GENDER, Boolean.parseBoolean(params.get(User_.GENDER)), SearchOperation.GENDER));
        }

        if (params.containsKey(User_.ADDRESS)) {
            specification.add(new SearchCriteria(User_.ADDRESS, params.get(User_.ADDRESS), SearchOperation.MATCH));
        }

        if (params.containsKey(Candidate_.YEARS_EXP)) {
            specification.add(new SearchCriteria(Candidate_.YEARS_EXP, params.get(Candidate_.YEARS_EXP), SearchOperation.YEARS_EXP));
        }

        if (params.containsKey(Candidate_.CV)) {
            specification.add(new SearchCriteria(Candidate_.CV, Boolean.parseBoolean(params.get(Candidate_.CV)), SearchOperation.CV));
        }

        return specification;
    }

    public JobSpecification jobSpecification(Map<String, String> params) {
        JobSpecification specification = new JobSpecification();

        if (params.containsKey(JobEnum.TITLE)) {
            specification.add(new SearchCriteria(JobEnum.TITLE, params.get(JobEnum.TITLE), SearchOperation.MATCH));
        }

        if (params.containsKey(JobEnum.BEGINNING_SALARY)) {
            specification.add(new SearchCriteria(JobEnum.BEGINNING_SALARY, params.get(JobEnum.BEGINNING_SALARY), SearchOperation.GREATER_THAN_EQUAL));
        }

        if (params.containsKey(JobEnum.ENDING_SALARY)) {
            specification.add(new SearchCriteria(JobEnum.ENDING_SALARY, params.get(JobEnum.ENDING_SALARY), SearchOperation.LESS_THAN_EQUAL));
        }

        if (params.containsKey(JobEnum.CATEGORY_ID)) {
            specification.add(new SearchCriteria(JobEnum.CATEGORY_ID, params.get(JobEnum.CATEGORY_ID), SearchOperation.CATEGORY_ID));
        }

        if (params.containsKey(JobEnum.JOB_TYPE_ID)) {
            specification.add(new SearchCriteria(JobEnum.JOB_TYPE_ID, params.get(JobEnum.JOB_TYPE_ID), SearchOperation.JOB_TYPE_ID));
        }

        if (params.containsKey(JobEnum.POSITION_ID)) {
            specification.add(new SearchCriteria(JobEnum.POSITION_ID, params.get(JobEnum.POSITION_ID), SearchOperation.POSITION_ID));
        }

        if (params.containsKey(JobEnum.PROVINCE_ID)) {
            specification.add(new SearchCriteria(JobEnum.PROVINCE_ID, params.get(JobEnum.PROVINCE_ID), SearchOperation.PROVINCE_ID));
        }

        if (params.containsKey(JobEnum.IS_APPLYING)) {
            specification.add(new SearchCriteria(JobEnum.IS_APPLYING, params.get(JobEnum.IS_APPLYING), SearchOperation.IS_APPLYING));
        }

        return specification;
    }

}
