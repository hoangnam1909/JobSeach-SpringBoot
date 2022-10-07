package com.nhn.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateRequest {

    private String username;
    private int yearsExp;
    private String linkedin;
    private String cv;

}
