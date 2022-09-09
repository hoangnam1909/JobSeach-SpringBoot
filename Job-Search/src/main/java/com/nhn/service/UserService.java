package com.nhn.service;

import com.nhn.models.dto.UserDTO;
import com.nhn.models.request.UpdateUserReq;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO updateUser(int id, UpdateUserReq req);

}
