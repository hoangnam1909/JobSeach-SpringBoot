package com.nhn.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsertCommentRequest {

    private int companyId;
    private int candidateId;
    private String content;

}
