package com.nhn.model.request.authed_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String avatar;

    private String fullName;

    private String email;

    private String phone;

    private Date dob;

    private boolean gender;

    private String address;

}