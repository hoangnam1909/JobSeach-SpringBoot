package com.nhn.repository;

import com.nhn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>,
        UserCustomRepository, JpaSpecificationExecutor<User> {

    List<User> findByUsername(String username);

}
