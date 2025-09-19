package com.TalentBridge.model;

import com.TalentBridge.utils.constants.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Application {
    @Id
    @Column(name = "applicationId", nullable = false, unique = true)
    private String id ; // application id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candId", referencedColumnName = "candId")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", referencedColumnName = "jobId")
    private Job job;

    @Enumerated(EnumType.STRING)
    private Status status; // APPLIED, UNDER_REVIEW, INTERVIEW_SCHEDULED, OFFERED, REJECTED, HIRED.

    private String appliedDate;
    private String lastUpdated;
}

