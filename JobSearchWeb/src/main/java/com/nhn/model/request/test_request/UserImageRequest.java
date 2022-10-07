package com.nhn.model.request.test_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserImageRequest {

    private String username;
    private String password;
    private String email;
    private String role;

}
