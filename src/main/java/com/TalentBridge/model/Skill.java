package com.TalentBridge.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Skill {

    @Id
    @Column(name = "skillId", nullable = false, unique = true)
    private String id;

    private String description;
    private String code;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_id")
//    private Job job;
}
