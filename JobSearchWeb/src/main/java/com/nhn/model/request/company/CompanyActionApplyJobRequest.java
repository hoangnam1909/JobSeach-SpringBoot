package com.nhn.model.request.company;

import com.nhn.valid.CompanyActionApplyJob;
import com.nhn.valid.CompanyUsername;
import com.nhn.valid.ExistingApplyJobId;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CompanyActionApplyJob
public class CompanyActionApplyJobRequest {

    @ExistingApplyJobId
    Integer applyJobId;

    @CompanyUsername
    String companyUsername;

}
