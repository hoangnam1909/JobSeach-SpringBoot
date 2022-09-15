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
public class UserDTO {

    private int id;
    private String username;
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

}
