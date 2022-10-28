package com.nhn.specifications;

import com.nhn.common.SearchCriteria;
import com.nhn.entity.Candidate;
import com.nhn.entity.Candidate_;
import com.nhn.entity.User;
import com.nhn.specifications.key.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

//@Component
public class UserSpecification implements Specification<User> {

    private List<SearchCriteria> list;

    public UserSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
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

            } else if (criteria.getOperation().equals(SearchOperation.YEARS_EXP)) {

                Join<Candidate, User> candidateUserJoin = root.join("candidate");
                predicates.add(builder.greaterThanOrEqualTo(candidateUserJoin.get(Candidate_.YEARS_EXP), criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.GENDER)) {

                predicates.add(builder.equal(
                        root.get(criteria.getKey()).as(Boolean.class), criteria.getValue()));

            } else if (criteria.getOperation().equals(SearchOperation.CV)) {

                Join<Candidate, User> candidateUserJoin = root.join("candidate");
                System.err.println(Boolean.parseBoolean(criteria.getValue().toString()));
                if (Boolean.parseBoolean(criteria.getValue().toString())) {
                    System.err.println("is not empty");
                    predicates.add(builder.isNotNull(candidateUserJoin.get(Candidate_.CV)));
                } else {
                    System.err.println("is empty");
                    predicates.add(builder.isNull(candidateUserJoin.get(Candidate_.CV)));
                }
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));

    }

}
