package com.nhn.model.request.admin_request.job;

import com.nhn.model.request.RequirementRequest;
import com.nhn.valid.ExistingJobId;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUpdateJobRequest {

    @ExistingJobId
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

    private boolean available;

    private Set<Integer> tagsId;

    private Set<RequirementRequest> requirements;

}
