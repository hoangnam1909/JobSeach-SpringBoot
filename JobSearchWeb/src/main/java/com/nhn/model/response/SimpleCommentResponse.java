package com.nhn.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCommentResponse {

    private String fullName;
    private String avatar;
    private String content;
    private Date createdDate;

}
