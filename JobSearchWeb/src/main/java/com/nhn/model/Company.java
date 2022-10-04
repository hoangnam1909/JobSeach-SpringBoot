package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "company", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
//    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "company_name")
    private String companyName = "n/a";

    @Basic
    @Column(name = "company_size")
    private int companySize;

    @Basic
    @Column(name = "contact_name")
    private String contactName;

    @Basic
    @Column(name = "contact_tel")
    private String contactTel;

    @Basic
    @Column(name = "contact_email")
    private String contactEmail;

    @Basic
    @Column(name = "contact_address")
    private String contactAddress;

    @Basic
    @Column(name = "introduction")
    private String introduction;

    @Basic
    @Column(name = "founded_year")
    private int foundedYear;

    @Basic
    @Column(name = "headquarters")
    private String headquarters;

    @Basic
    @Column(name = "link")
    private String link;

    @ManyToMany
    @JoinTable(
            name = "company_industry",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id"))
    @JsonManagedReference
    private Set<Industry> industries;

//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
//    private Collection<Comment> comments;

//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<CompanyIndustry> companyIndustrySet;

}
