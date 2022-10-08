package com.nhn.model.request;

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
public class JobUpdateRequest {

    private int id;
    private String title;
    private String description;
    private Date jobStartDate;
    private String address;
    private int provinceId;
    private int noOfVacancies;
    private int positionId;
    private int jobCategoryId;
    private int jobTypeId;
    private String salary;
    private List<Integer> tagsId;
    private List<RequirementRequest> requirements;

}
