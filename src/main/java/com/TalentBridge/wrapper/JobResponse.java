package com.TalentBridge.wrapper;

import com.TalentBridge.model.Job;
import com.TalentBridge.utils.constants.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    private Job job;
    private String createdBy;
    private JobStatus status;
}

