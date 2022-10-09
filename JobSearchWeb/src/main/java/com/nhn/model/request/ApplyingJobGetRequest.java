package com.nhn.model.request;

import com.nhn.valid.CompanyJob;
import com.nhn.valid.CompanyUserId;
import com.nhn.valid.ExistingJobId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@CompanyJob
public class ApplyingJobGetRequest {

    @ExistingJobId
    Integer jobId;

    @CompanyUserId
    Integer companyUserId;

}
