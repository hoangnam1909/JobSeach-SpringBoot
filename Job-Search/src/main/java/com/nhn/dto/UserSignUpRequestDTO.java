package com.nhn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDTO {

    private String username;
    private String password;
    private String avatar;
    private String fullName;
    private String email;
    private String phone;
    private Date dob;
    private boolean gender;
    private String address;
    private String userType;
    private int employerId;
    private int candidateId;

}
