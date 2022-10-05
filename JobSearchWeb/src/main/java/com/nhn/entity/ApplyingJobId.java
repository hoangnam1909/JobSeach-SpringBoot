package com.nhn.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ApplyingJobId implements Serializable {

    @Column(name = "job_id")
    @NotNull
    private int jobId;

    @Column(name = "candidate_user_id")
    @NotNull
    private int candidateUserId;

}
