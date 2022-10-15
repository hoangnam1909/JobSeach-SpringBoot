package com.nhn.model.request.candidate;

import com.nhn.valid.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateActionApplyJobRequest {

    @ExistingApplyJobId
    Integer applyJobId;

    @CandidateUsername
    String candidateUsername;

}
