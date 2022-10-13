package com.nhn.model.request.authed_request;

import com.nhn.valid.RegisteredUsername;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {

    @RegisteredUsername
    private String username;

    private String fullName;

    private String email;

    private String phone;

    private Date dob;

    private String gender;

    private String address;

}
