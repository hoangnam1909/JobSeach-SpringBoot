package com.nhn.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    private int id;
    private String username;
    private String password;
    private String avatar;
    private String userType;
    private boolean active;
    private String fullName;
    private String email;
    private String phone;
    private Date dob;
    private boolean gender;
    private String address;
    private Date joinedDate;
    private int employerId;
    private int candidateId;

}
