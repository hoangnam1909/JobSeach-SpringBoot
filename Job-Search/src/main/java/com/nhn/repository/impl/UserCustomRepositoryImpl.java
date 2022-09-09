package com.nhn.repository.impl;

import com.nhn.models.User;
import com.nhn.models.User_;
import com.nhn.repository.UserCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findByUsernameAndGender(String username, boolean gender) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate fullNamePredicate = criteriaBuilder.equal(userRoot.get(User_.USERNAME), username);
        Predicate genderPredicate = criteriaBuilder.equal(userRoot.get(User_.GENDER), gender);

        criteriaQuery.where(fullNamePredicate, genderPredicate);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
