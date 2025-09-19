package com.TalentBridge.wrapper;

import com.TalentBridge.model.Skill;
import com.TalentBridge.utils.constants.CreationStatus;
import com.TalentBridge.dto.SkillDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SkillResponse {
    private Skill skill;
    private CreationStatus status;
}
