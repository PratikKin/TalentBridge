package com.TalentBridge.wrapper;

import com.TalentBridge.wrapper.utility.ApplicationBody;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {
    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;
    @JsonProperty("Application")
    private ApplicationBody Application;
}
