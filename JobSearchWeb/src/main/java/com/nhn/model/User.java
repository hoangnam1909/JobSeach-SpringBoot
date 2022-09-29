package com.nhn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String role = Constant.USER_ROLE.NORMAL;
    @Basic
    @Column(name = "active")
    private boolean active = true;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "email")
    @Email
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @PrePersist
    public void onSave(){
        if (joinedDate == null)
            joinedDate = new Date();

        if (role.equals(Constant.USER_ROLE.COMPANY))
            active = false;
    }

}
