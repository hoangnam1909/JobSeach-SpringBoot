package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applying_job", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyingJob {

    @EmbeddedId
    private ApplyingJobId applyingJobId;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @MapsId("candidateUserId")
    @JoinColumn(name = "candidate_user_id")
    private User user;

    @Basic
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic
    @Column(name = "status")
    private String status;

//    @ManyToOne
//    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
//    private Job job;
//
//    @ManyToOne
//    @JoinColumn(name = "candidate_user_id", referencedColumnName = "id", nullable = false)
//    private User user;

}
