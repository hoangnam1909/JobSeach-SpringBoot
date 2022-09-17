package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "work_experience", schema = "jobsearchingnew", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "from_date")
    private String fromDate;

    @Basic
    @Column(name = "to_date")
    private String toDate;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Candidate candidate;

}
