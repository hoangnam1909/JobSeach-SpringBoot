package com.nhn.model.request.company;

import com.nhn.valid.CompanyJob;
import com.nhn.valid.CompanyUsername;
import com.nhn.valid.ExistingJobId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@CompanyJob
public class CompanyJobRequest {

    @ExistingJobId
    Integer jobId;

    @CompanyUsername
    String companyUsername;

}
