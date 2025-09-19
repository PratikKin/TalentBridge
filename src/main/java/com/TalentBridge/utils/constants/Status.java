package com.TalentBridge.utils.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("APPLIED")
    APPLIED,
    @JsonProperty("UNDER_REVIEW")
    UNDER_REVIEW,
    @JsonProperty("INTERVIEW_SCHEDULED")
    INTERVIEW_SCHEDULED,
    @JsonProperty("OFFERED")
    OFFERED,
    @JsonProperty("REJECTED")
    REJECTED,
    @JsonProperty("HIRED")
    HIRED
}