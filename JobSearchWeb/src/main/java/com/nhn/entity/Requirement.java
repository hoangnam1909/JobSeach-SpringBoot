package com.nhn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "requirement", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requirement {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "job_id", insertable = false, updatable = false)
    @JsonIgnore
    private int jobId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
    private Job job;

    public Requirement(String content, Job job) {
        this.content = content;
        this.job = job;
    }

}
