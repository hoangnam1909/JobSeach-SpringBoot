package com.nhn.model.request;

import com.nhn.valid.CandidateApplyJob;
import com.nhn.valid.CandidateUsername;
import com.nhn.valid.ExistingJobId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@CandidateApplyJob
public class CandidateApplyJobRequest {

    @ExistingJobId
    int jobId;

    @CandidateUsername
    String candidateUsername;

}
