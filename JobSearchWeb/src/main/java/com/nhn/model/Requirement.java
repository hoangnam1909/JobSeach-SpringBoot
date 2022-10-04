package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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
    private int id;

    @Basic
    @Column(name = "content")

    private String content;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
    private Job job;

    public Requirement(String content, Job job) {
        this.content = content;
        this.job = job;
    }

}
