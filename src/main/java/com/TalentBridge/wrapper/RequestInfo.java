package com.TalentBridge.wrapper;

import com.TalentBridge.utils.constants.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo {
    @JsonProperty("userId")
    private String id;
    @JsonProperty("role")
    private Role role;
}
