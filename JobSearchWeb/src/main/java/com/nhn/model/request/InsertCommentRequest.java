package com.nhn.model.request;

import com.nhn.valid.CompanyUserId;
import com.nhn.valid.ExistingCandidateId;
import com.nhn.valid.ExistingCompanyId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsertCommentRequest {

    @CompanyUserId
    private Integer companyUserId;

    private String content;

}
