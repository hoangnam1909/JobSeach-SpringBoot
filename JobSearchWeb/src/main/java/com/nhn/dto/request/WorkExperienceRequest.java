package com.nhn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceRequest {

    private String username;

    private Date fromDate;

    private Date toDate;

    private String content;

    private String position;

}
