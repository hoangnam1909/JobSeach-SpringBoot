package com.nhn.service;

import com.nhn.dto.UserDTO;
import com.nhn.dto.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public interface UserService extends UserDetailsService {

    UserDTO updateUser(int id, UserUpdateRequest req);

}
