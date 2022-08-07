package com.nhn.JobSearchSpringBoot.repository;

import com.nhn.JobSearchSpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);

}
