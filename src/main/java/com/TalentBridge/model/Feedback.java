package com.TalentBridge.model;

import com.TalentBridge.utils.constants.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    private String id;

    private String level;

    private Status status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "application_id")
//    private Application application;

    private String applicationId;

    private String comments;
    private String createdAt;
    private String updatedAt;
}
