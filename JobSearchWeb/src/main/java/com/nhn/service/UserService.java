package com.nhn.service;

import com.nhn.dto.UserDTO;
import com.nhn.dto.request.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO updateUser(int id, UserUpdateRequest req);

}
