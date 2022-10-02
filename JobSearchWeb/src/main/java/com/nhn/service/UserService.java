package com.nhn.service;

import com.nhn.dto.UserDTO;
import com.nhn.dto.request.AdminUserInsertRequest;
import com.nhn.dto.request.UserUpdateRequest;
import com.nhn.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {

    UserDTO add(AdminUserInsertRequest req);

    UserDTO updateUser(int id, UserUpdateRequest req);

    User currentUser();

}
