package com.nhn.model.request.admin_request.user;

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
public class AdminUpdateUserRequest {

    private String rawPassword;

    private String fullName;

    @NotBlank
    @Email
    private String email;

    @Size(min = 9, max = 12)
    private String phone;

    private Date dob;

    private boolean gender;

    private String address;

    private String role;

    private boolean active;

}
