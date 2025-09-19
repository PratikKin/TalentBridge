
package com.TalentBridge.dto;

import com.TalentBridge.utils.constants.EmpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateDTO extends UserDTO {
    private String Id= null;  // from backend -> pk but fk for user
    private EmpStatus status;     // from backend
//    private List<String> applicationIds;
//    private String appliedDate;
    private String lastUpdated;
}