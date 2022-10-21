package com.nhn.model.request.admin_request.candidate;

import com.nhn.valid.UnregisteredEmail;
import com.nhn.valid.UnregisteredPhoneNumber;
import com.nhn.valid.UnregisteredUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminAddUserRequest {

    @NotBlank
    @UnregisteredUsername
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    @UnregisteredEmail
    private String email;

    private String fullName;

    @UnregisteredPhoneNumber
    @Size(min = 9, max = 12)
    private String phone;

    private Date dob;

    private boolean gender;

    private String address;
}
