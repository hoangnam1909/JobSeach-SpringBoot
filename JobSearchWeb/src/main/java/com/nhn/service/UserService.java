package com.nhn.service;

import com.nhn.dto.UserDTO;
import com.nhn.dto.request.AdminUserInsertRequest;
import com.nhn.dto.request.authed_request.UpdateUserRequest;
import com.nhn.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO add(AdminUserInsertRequest request);

    UserDTO updateUser(String username, UpdateUserRequest request);

    User currentUser();

}
