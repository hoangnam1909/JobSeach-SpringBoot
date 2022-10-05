package com.nhn.repository;

import com.nhn.entity.Candidate;
import com.nhn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>,
        JpaSpecificationExecutor<User> {

    User findUserByUsername(String username);

    List<User> findByUsername(String username);

    User findOneByUsernameEqualsIgnoreCase(String username);

    User findOneByCandidate(Candidate candidate);

    List<User> findUserByRole(String role);

}
