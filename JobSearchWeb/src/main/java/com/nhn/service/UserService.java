package com.nhn.service;

import com.nhn.model.UserDTO;
import com.nhn.model.request.AdminUserInsertRequest;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import com.nhn.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO add(AdminUserInsertRequest request);

    UserDTO updateUser(String username, UpdateUserRequest request);

    User currentUser();

}
