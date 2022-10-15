package com.nhn.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhn.entity.Candidate;
import com.nhn.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserResponse {

    private String username;

    private String avatar;

    private String role;

    private String fullName;

    private String email;

    private String phone;

    private Date dob;

    private boolean gender;

    private String address;

    private Date joinedDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Candidate candidate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Company company;

}
