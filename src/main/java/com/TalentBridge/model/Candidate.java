package com.TalentBridge.model;

import com.TalentBridge.utils.constants.EmpStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @Column(name = "candId", nullable = false, unique = true)
    private String candId;  // from backend -> pk but fk for user

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId", referencedColumnName = "userId")
//    private User user;

    @Enumerated(EnumType.STRING)
    private EmpStatus status;     // from backend

//    @ElementCollection
//    @CollectionTable(name = "candidate_applications", joinColumns = @JoinColumn(name = "candId"))
//    @Column(name = "application_id")
//    private List<String> applicationIds;

//    private String appliedDate;
    private String lastUpdated;

}