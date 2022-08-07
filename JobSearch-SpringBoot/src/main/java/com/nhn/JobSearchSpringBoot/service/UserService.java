package com.nhn.JobSearchSpringBoot.service;

import com.nhn.JobSearchSpringBoot.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserDTO> getListUsers();

}
