package com.nhn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhn.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Basic
    @Column(name = "username")
    @NotNull
    private String username;

    @Basic
    @Column(name = "password")
    @NotNull
    @JsonIgnore
    private String password;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "role")
    private String role;

    @Basic
    @Column(name = "active")
    private boolean active = true;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "dob")
    private Date dob;

    @Basic
    @Column(name = "gender")
    private boolean gender = false;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "joined_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

//    @OneToMany(mappedBy = "user")
//    private Collection<ApplyingJob> applyingJobs;

//    @OneToMany(mappedBy = "user")
//    private Collection<Job> jobs;

    @PrePersist
    public void onSave() {
        if (joinedDate == null)
            joinedDate = new Date();

        if (role.equals(Constant.USER_ROLE.COMPANY))
            active = false;

        if (role.length() == 0)
            role = Constant.USER_ROLE.NORMAL;

        if (avatar == null)
            avatar = "https://res.cloudinary.com/nhn1909/image/upload/v1642074622/tb-avatar-none_r1c2ye.jpg";
    }

}
