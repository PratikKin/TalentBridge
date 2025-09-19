package com.TalentBridge.dto;

import com.TalentBridge.utils.constants.JobStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private String id;

    private String companyName;
    private String title;

    private List<SkillDTO> skillDTOS;

    private List<String> qualification;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private String location;
    private String salary;
    private String experience;
    private String description;
    private String postedDate;
    private String lastUpdated;
}
