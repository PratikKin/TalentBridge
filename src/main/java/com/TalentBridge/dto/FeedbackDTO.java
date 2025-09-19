package com.TalentBridge.dto;

import com.TalentBridge.utils.constants.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    @Id
    private String id;

    private String level;

    private Status status;

    private ApplicationDTO applicationDTO;

    private String comments;
    private String createdAt;
    private String updatedAt;
}
