package com.nhn.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserReq {

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
    private int employerId;
    private int candidateId;

}
