package com.nhn.JobSearchSpringBoot.model.dto;

import com.nhn.JobSearchSpringBoot.model.Candidate;
import com.nhn.JobSearchSpringBoot.model.Employer;
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

    private String username;
    private String avatar;
    private String userType;
    private byte active;
    private String fullName;
    private String email;
    private String phone;
    private Date dob;
    private Boolean gender;
    private String address;
    private Date joinedDate;
    private Candidate candidate;
    private Employer employer;

}
