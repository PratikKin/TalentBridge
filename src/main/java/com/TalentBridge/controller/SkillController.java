package com.TalentBridge.controller;
import com.TalentBridge.model.Skill;
import com.TalentBridge.service.SkillService;
import com.TalentBridge.utils.constants.CreationStatus;
import com.TalentBridge.wrapper.SkillResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/create")
    public ResponseEntity<SkillResponse> createSkill(@RequestBody Skill skill) {
        return ResponseEntity.ok(skillService.createSkill(skill));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(Map.of(
                "skills", skills,
                "count", skills.size()
        ));
    }
}
