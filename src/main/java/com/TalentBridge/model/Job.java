package com.TalentBridge.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Job {

    @Id
    @Column(name = "jobId", nullable = false, unique = true)
    private String id;

    private String companyName;
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_id")  // foreign key will be in Skill table
    private List<Skill> skills;

    @ElementCollection
    @CollectionTable(name = "job_qualifications", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "qualification")
    private List<String> qualification;

    private String location;
    private String salary;
    private String experience;
    private String description;
    private String postedDate;
    private String lastUpdated;
}
