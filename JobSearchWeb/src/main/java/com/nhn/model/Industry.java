package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "industry", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Industry {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "industry", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    Set<CompanyIndustry> companyIndustrySet;

    @ManyToMany(mappedBy = "industries")
    @JsonBackReference
    private List<Company> companies;

}
