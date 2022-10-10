package com.nhn.model.request;

import com.nhn.valid.CompanyUsername;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateJobRequest {

    @CompanyUsername
    private String companyUsername;
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
    private Set<Integer> tagsId;
    private Set<RequirementRequest> requirements;

}
