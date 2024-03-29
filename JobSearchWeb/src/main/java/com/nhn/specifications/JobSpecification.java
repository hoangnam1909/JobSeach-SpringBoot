package com.nhn.specifications;

import com.nhn.common.SearchCriteria;
import com.nhn.entity.*;
import com.nhn.specifications.key.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class JobSpecification implements Specification<Job> {

    private List<SearchCriteria> list;

    public JobSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {

                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {

                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {

                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {

                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {

                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));

            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {

                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));

            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {

                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));

            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {

                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));

            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {

                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));

            } else if (criteria.getOperation().equals(SearchOperation.IN)) {

                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));

            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {

                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));

            } else if (criteria.getOperation().equals(SearchOperation.CATEGORY_ID)) {

                Join<JobCategory, Job> categoryJobs = root.join("jobCategory");
                predicates.add(builder.equal(categoryJobs.get("id"), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.JOB_TYPE_ID)) {

                Join<JobType, Job> jobTypeJobs = root.join("jobType");
                predicates.add(builder.equal(jobTypeJobs.get("id"), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.PROVINCE_ID)) {

                Join<Province, Job> provinceJobs = root.join("province");
                predicates.add(builder.equal(provinceJobs.get("id"), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.POSITION_ID)) {

                Join<Position, Job> positionJobs = root.join("position");
                predicates.add(builder.equal(positionJobs.get("id"), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.COMPANY_USER_ID)) {

                Join<User, Job> companyUserJobs = root.join("companyUser");
                predicates.add(builder.equal(companyUserJobs.get("id"), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.COMPANY_USERNAME)) {

                Join<User, Job> companyUserJobs = root.join("companyUser");
                predicates.add(builder.equal(companyUserJobs.get("username"), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.IS_APPLYING)) {

                if (Boolean.parseBoolean(criteria.getValue().toString())) {
                    predicates.add(builder.greaterThan(
                            builder.size(root.get("applyingJobs")), 0));
                }

            } else if (criteria.getOperation().equals(SearchOperation.AVAILABLE)) {
                System.err.println(root.get(criteria.getKey()).as(Boolean.class));
                System.err.println(root.get(criteria.getKey()).as(Boolean.class).getClass());

                System.err.println(criteria.getValue());
                System.err.println(criteria.getValue().getClass());
                predicates.add(builder.equal(
                        root.get(criteria.getKey()).as(Boolean.class), criteria.getValue()));

            }

        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }


}
