package com.TalentBridge.wrapper.utility;

import com.TalentBridge.model.Application;
import lombok.*;

@Getter
@Setter
public class ApplicationResponse {
    private Application application;
    private String status;
}
