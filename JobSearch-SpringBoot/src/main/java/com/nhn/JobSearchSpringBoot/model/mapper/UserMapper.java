package com.nhn.JobSearchSpringBoot.model.mapper;

import com.nhn.JobSearchSpringBoot.model.User;
import com.nhn.JobSearchSpringBoot.model.dto.UserDTO;

public class UserMapper {

    public static UserDTO toUserDto(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setUserType(user.getUserType());
        userDTO.setActive(user.getActive());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setDob(user.getDob());
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        userDTO.setJoinedDate(user.getJoinedDate());
        userDTO.setCandidate(user.getCandidate());
        userDTO.setEmployer(user.getEmployer());

        return userDTO;
    }

}
