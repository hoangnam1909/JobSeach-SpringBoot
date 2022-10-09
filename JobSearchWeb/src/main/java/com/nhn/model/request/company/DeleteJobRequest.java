package com.nhn.model.request.company;

import com.nhn.valid.CompanyUserId;
import com.nhn.valid.DeleteJobRequestValid;
import com.nhn.valid.ExistingJobId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DeleteJobRequestValid
public class DeleteJobRequest {

    @ExistingJobId
    Integer jobId;

    @CompanyUserId
    Integer companyUserId;

}
