package com.nhn.dto.request;

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
