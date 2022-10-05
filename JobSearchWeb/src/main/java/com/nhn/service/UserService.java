package com.nhn.service;

import com.nhn.dto.UserDTO;
import com.nhn.dto.request.AdminUserInsertRequest;
import com.nhn.dto.request.UserUpdateRequest;
import com.nhn.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO add(AdminUserInsertRequest req);

    UserDTO updateUser(int id, UserUpdateRequest req);

    User currentUser();

}
