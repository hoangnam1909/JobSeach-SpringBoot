package com.nhn.specifications;

import com.nhn.models.User;
import com.nhn.models.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    public static Specification<User> hasUsername(String username){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(User_.USERNAME), username);
        });
    }

    public static Specification<User> containsUsername(String username){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get(User_.USERNAME), "%" + username + "%");
        });
    }

    public static Specification<User> containsFullName(String fullName){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get(User_.FULL_NAME), "%" + fullName + "%");
        });
    }

    public static Specification<User> hasGender(boolean gender){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(User_.GENDER), gender);
        });
    }

}
