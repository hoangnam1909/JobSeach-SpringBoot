package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "company_industry", schema = "jobsearchingnew", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyIndustry {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "company_id")
    private int companyId;

    @Basic
    @Column(name = "industry_id")
    private int industryId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
//    private Company company;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "industry_id", referencedColumnName = "id", nullable = false)
//    private Industry industry;

}
