package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "job_category", schema = "jobsearchingnew", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobCategory {

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

//    @OneToMany(mappedBy = "jobCategory")
//    private Collection<JobEntity> jobsById;

}
