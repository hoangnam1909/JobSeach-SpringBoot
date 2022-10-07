package com.nhn.dto.request;

import com.nhn.valid.UnregisteredEmail;
import com.nhn.valid.UnregisteredPhoneNumber;
import com.nhn.valid.UnregisteredUsername;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUserInsertRequest {

    @UnregisteredUsername
    private String username;
    private String password;
    private String avatar;
    private String role;
    private boolean active;
    private String fullName;
    @UnregisteredEmail
    private String email;
    @UnregisteredPhoneNumber
    private String phone;
    private Date dob;
    private boolean gender;
    private String address;

}
