package com.nhn.model.request.authed_request;

import com.nhn.valid.RegisteredUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {

//    @RegisteredUsername
//    private String username;

    private String fullName;

    @Email
    private String email;

    @Size(min = 9, max = 12)
    private String phone;

    private Date dob;

    private String gender;

    private String address;

}
