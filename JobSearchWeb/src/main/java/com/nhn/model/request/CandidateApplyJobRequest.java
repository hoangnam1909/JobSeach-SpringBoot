package com.nhn.model.request;

import com.nhn.valid.ExistingCandidateUserId;
import com.nhn.valid.ExistingJobId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateApplyJobRequest {

    @ExistingJobId
    int jobId;

    @ExistingCandidateUserId
    int candidateUserId;

}
