package com.nhn.JobSearchSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "years_experience")
    private Integer yearsExperience;

    @Basic
    @Column(name = "strengths")
    private String strengths;

    @Basic
    @Column(name = "weaknesses")
    private String weaknesses;

    @Basic
    @Column(name = "majoring")
    private String majoring;

    @Basic
    @Column(name = "majoring_detail")
    private String majoringDetail;

    @Basic
    @Column(name = "language_certificate")
    private String languageCertificate;

    @Basic
    @Column(name = "informatics_certificate")
    private String informaticsCertificate;

    @Basic
    @Column(name = "cv")
    private String cv;

}
