package com.nhn.entity;

import com.nhn.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "apply_job", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJob {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
//    @JsonBackReference
    private Job jobApplied;

    @ManyToOne
    @JoinColumn(name = "candidate_user_id", referencedColumnName = "id", nullable = false)
    private User candidateUser;

    @PrePersist
    public void onSave() {
        Date currentDate = new Date();

        if (createdDate == null)
            createdDate = currentDate;

        status = Constant.APPLYING_STATUS.PENDING;
    }

}
