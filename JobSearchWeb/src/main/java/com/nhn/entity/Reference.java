package com.nhn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reference", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reference {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "name")
    private String name = "n/a";

    @Basic
    @Column(name = "link")
    private String link = "n/a";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Candidate candidate;

    public Reference(String name, String link, Candidate candidate) {
        this.name = name;
        this.link = link;
        this.candidate = candidate;
    }

}
