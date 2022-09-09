package com.nhn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "user_type")
    private String userType;
    @Basic
    @Column(name = "active")
    private boolean active;
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
    private boolean gender;
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
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;

}
