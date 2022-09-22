package com.nhn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInsertRequest {

    private String username;
    private String password;
    private String avatar;
    private String role;
    private boolean active;
    private String fullName;
    private String email;
    private String phone;
    private Date dob;
    private boolean gender;
    private String address;

}
