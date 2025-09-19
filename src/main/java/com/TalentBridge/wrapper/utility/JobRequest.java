package com.TalentBridge.wrapper.utility;

import com.TalentBridge.controller.JobController;
import com.TalentBridge.wrapper.RequestInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
    private RequestInfo requestInfo;
    private JobDetails job;
}