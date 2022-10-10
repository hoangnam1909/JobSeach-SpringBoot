package com.nhn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "job", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "title")
    @NotNull
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "date_published")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublished;

    @Basic
    @Column(name = "job_start_date")
    @Temporal(TemporalType.DATE)
    private Date jobStartDate;

    @Basic
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Basic
    @Column(name = "no_of_vacancies")
    private int noOfVacancies;

    @Basic
    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = false)
    private Province province;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private JobCategory jobCategory;

    @ManyToOne
    @JoinColumn(name = "job_type_id", referencedColumnName = "id", nullable = false)
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name = "user_company_id", referencedColumnName = "id", nullable = false)
    private User companyUser;

    @Basic
    @Column(name = "salary")
    private String salary;

    @Basic
    @Column(name = "is_available")
    private boolean available;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "job_tag",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonManagedReference
    Set<Tag> tags;

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Requirement> requirements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobApp")
    @JsonManagedReference
    private Collection<ApplyJob> applyingJobs;

    private int applyJobCounter;

    @PostLoad
    public void applyJobCount() {
        applyJobCounter = applyingJobs.size();
        applyingJobs = null;
    }

    @PrePersist
    public void onSave() {
        if (datePublished == null)
            datePublished = new Date();

        available = true;
    }

    @PostUpdate
    public void afterUpdate(){
        modifiedDate = new Date();
    }

}
