package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "candidate", schema = "jobsearchingnew", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "years_exp")
    private int yearsExp;

    @Basic
    @Column(name = "linkedin")
    private String linkedin;

    @Basic
    @Column(name = "cv")
    private String cv;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Language> languages;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Qualification> qualifications;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Reference> references;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Skill> skills;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Talent> talents;

}
