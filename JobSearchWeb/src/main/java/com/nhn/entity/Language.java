package com.nhn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "language", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Candidate candidate;

    public Language(String name, String description, Candidate candidate) {
        this.name = name;
        this.description = description;
        this.candidate = candidate;
    }
}
