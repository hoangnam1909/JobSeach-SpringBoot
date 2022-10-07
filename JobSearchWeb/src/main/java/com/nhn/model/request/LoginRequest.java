package com.nhn.model.request;

import com.nhn.valid.RegisteredUsername;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    @RegisteredUsername
    private String username;

    @NotBlank
    private String password;

}
