package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company", schema = "jobsearchingnew", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "company_name")
    private String companyName;

    @Basic
    @Column(name = "company_size")
    private Integer companySize;

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
    private Integer foundedYear;

    @Basic
    @Column(name = "headquarters")
    private String headquarters;

    @Basic
    @Column(name = "link")
    private String link;

//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<CompanyIndustry> companyIndustrySet;

    @ManyToMany
    @JoinTable(
            name = "company_industry",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id"))
    @JsonManagedReference
    Set<Industry> industries;

}
