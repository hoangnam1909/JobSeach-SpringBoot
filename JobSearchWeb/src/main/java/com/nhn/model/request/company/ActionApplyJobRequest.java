package com.nhn.model.request.company;

import com.nhn.valid.ActionApplyJob;
import com.nhn.valid.CompanyUsername;
import com.nhn.valid.ExistingApplyJobId;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ActionApplyJob
public class ActionApplyJobRequest {

    @ExistingApplyJobId
    Integer applyJobId;

    @CompanyUsername
    String companyUsername;

}
