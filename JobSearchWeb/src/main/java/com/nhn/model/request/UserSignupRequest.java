package com.nhn.model.request;

import com.nhn.valid.UnregisteredEmail;
import com.nhn.valid.UnregisteredUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {

    @NotBlank
    @UnregisteredUsername
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @UnregisteredEmail
    private String email;

    @NotBlank
    private String role;

}
