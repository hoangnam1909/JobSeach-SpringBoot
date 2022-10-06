package com.nhn.dto.request;

import com.nhn.valid.CompanyJob;
import com.nhn.valid.CompanyUserId;
import com.nhn.valid.JobId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@CompanyJob(message = "This job is not from this company ${validatedValue}")
public class ApplyingJobGetRequest {

    @JobId(message = "Job id: ${validatedValue} is not present")
    Integer jobId;

    @CompanyUserId(message = "Company user id: {validatedValue} is not present")
    Integer companyUserId;

}
