package com.nhn.model.request;

import com.nhn.valid.ExistingCandidateId;
import com.nhn.valid.ExistingCompanyId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsertCommentRequest {

    @ExistingCompanyId
    private Integer companyId;

    @ExistingCandidateId
    private Integer candidateId;

    private String content;

}
