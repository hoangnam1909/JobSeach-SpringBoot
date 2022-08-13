package com.nhn.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getMajoring() {
        return majoring;
    }

    public void setMajoring(String majoring) {
        this.majoring = majoring;
    }

    public String getMajoringDetail() {
        return majoringDetail;
    }

    public void setMajoringDetail(String majoringDetail) {
        this.majoringDetail = majoringDetail;
    }

    public String getLanguageCertificate() {
        return languageCertificate;
    }

    public void setLanguageCertificate(String languageCertificate) {
        this.languageCertificate = languageCertificate;
    }

    public String getInformaticsCertificate() {
        return informaticsCertificate;
    }

    public void setInformaticsCertificate(String informaticsCertificate) {
        this.informaticsCertificate = informaticsCertificate;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
