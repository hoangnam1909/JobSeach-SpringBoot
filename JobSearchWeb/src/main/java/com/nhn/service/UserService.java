package com.nhn.service;

import com.nhn.entity.User;
import com.nhn.model.UserDTO;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {

    UserDTO addOrUpdate(User user, MultipartFile file);

//    UserDTO updateUser(String username, UpdateUserRequest request);

    User currentUser();

    User update(String username, UpdateUserRequest request, MultipartFile file);

}
