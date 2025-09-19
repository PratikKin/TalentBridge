package com.TalentBridge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recruiters")
public class Recruiter {
    @Id
    @Column(name = "recId", nullable = false, unique = true)
    private String recId;  // from backend -> pk but fk for user

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId", referencedColumnName = "userId")
//    private User user;

    private String companyName;
    private String title; // job title
    private String website; // company website
    private String location; // company location
    private String industry; // company industry
    private String about; // company about
    private String createdAt;
    private String updatedAt;

}