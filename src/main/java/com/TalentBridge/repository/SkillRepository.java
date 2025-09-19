package com.TalentBridge.repository;

import com.TalentBridge.dto.SkillDTO;
import com.TalentBridge.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
    Optional<Skill> findTopByOrderByIdDesc();
}
