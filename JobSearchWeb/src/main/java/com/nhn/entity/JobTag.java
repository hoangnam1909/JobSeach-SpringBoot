package com.nhn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "job_tag", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "job_id")
    private int jobId;

    @Basic
    @Column(name = "tag_id")
    private int tagId;

    public JobTag(int jobId, int tagId) {
        this.jobId = jobId;
        this.tagId = tagId;
    }
}
