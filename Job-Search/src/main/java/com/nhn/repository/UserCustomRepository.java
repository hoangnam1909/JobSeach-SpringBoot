package com.nhn.repository;

import com.nhn.models.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> findByUsernameAndGender(String username, boolean gender);
}
