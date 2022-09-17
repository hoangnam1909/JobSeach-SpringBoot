package com.nhn.repository;

import com.nhn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>,
        UserCustomRepository, JpaSpecificationExecutor<User> {

    User findUserByUsername(String username);

    List<User> findByUsername(String username);

    User findOneByUsernameEqualsIgnoreCase(String username);

    User findOneByUsernameIgnoreCaseAndAndPassword(String username, String password);

    List<User> findUserByRole(String role);

}
