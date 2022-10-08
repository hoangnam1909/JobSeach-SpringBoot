package com.nhn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "province", schema = "jobsearchingnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "name_en")
    private String nameEn;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "full_name_en")
    private String fullNameEn;

    @Basic
    @Column(name = "code_name")
    private String codeName;

}
