package com.nhn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private String companyUsername;
    private String title;
    private String description;
    private Date jobStartDate;
    private String location;
    private int noOfVacancies;
    private int positionId;
    private int jobCategoryId;
    private int jobTypeId;
    private String salary;
    private List<RequirementRequest> requirements;

}
