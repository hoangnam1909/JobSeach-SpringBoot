package com.nhn.model.response;

import com.nhn.entity.Job;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateApplyJobResponse {

    private int id;

    private Date createdDate;

    private String status;

    private Job jobApplied;

}
