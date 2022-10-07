package com.nhn.dto.request;

import com.nhn.valid.UnregisteredEmail;
import com.nhn.valid.UnregisteredUsername;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestRequest {

    @UnregisteredUsername
    private String username;

    @NotBlank
    @UnregisteredEmail
    private String email;

//    @UnregisteredPhoneNumber
//    private String phone;

}
