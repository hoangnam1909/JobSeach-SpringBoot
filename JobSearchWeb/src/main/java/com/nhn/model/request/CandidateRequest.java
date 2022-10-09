package com.nhn.model.request;

import com.nhn.valid.CandidateUsername;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateRequest {

    @CandidateUsername
    private String candidateUsername;

    private int yearsExp;

    private String linkedin;

    private String cv;


}
