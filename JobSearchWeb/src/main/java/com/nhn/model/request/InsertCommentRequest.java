package com.nhn.model.request;

import com.nhn.valid.CompanyUserId;
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
