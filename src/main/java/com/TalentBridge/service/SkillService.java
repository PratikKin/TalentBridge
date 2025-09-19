package com.TalentBridge.service;

import com.TalentBridge.model.Skill;
import com.TalentBridge.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }


    public Skill createSkill(Skill skill) {
        if (skill.getId() == null || skill.getId().isEmpty()) {
            // Fetch last skill ID
            String lastSkillId = skillRepository.findTopByOrderByIdDesc()
                    .map(Skill::getId)
                    .orElse(null);

            int nextIdNumber = 1;
            if (lastSkillId != null && lastSkillId.startsWith("Skill_")) {
                try {
                    nextIdNumber = Integer.parseInt(lastSkillId.split("_")[1]) + 1;
                } catch (NumberFormatException e) {
                    nextIdNumber = 1; // fallback if parsing fails
                }
            }

            skill.setId("Skill_" + nextIdNumber);
        }

        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

}
