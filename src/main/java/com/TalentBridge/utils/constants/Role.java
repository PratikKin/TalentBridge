package com.TalentBridge.utils.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("GUEST")
    GUEST,
    @JsonProperty("RECRUITER")
    RECRUITER,
    @JsonProperty("CANDIDATE")
    CANDIDATE
}
