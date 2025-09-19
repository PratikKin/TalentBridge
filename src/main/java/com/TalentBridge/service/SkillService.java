package com.TalentBridge.service;

import com.TalentBridge.model.Skill;
import com.TalentBridge.wrapper.SkillResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final WebClient.Builder webClientBuilder;

    private static final String CREATE_SKILL_API = "http://localhost:8082/api/skills"; // external API URL

    public SkillResponse createSkill(Skill skill) {
        // Use WebClient to send the Skill object to external API
        return webClientBuilder.build()
                .post()
                .uri(CREATE_SKILL_API+"/create")
                .bodyValue(skill)
                .retrieve()
                .bodyToMono(SkillResponse.class)
                .block(); // blocking since service expects immediate result
    }



    public List<Skill> getAllSkills() {
        return webClientBuilder.build()
                .get()
                .uri(CREATE_SKILL_API+"/get")
                .retrieve()
                .bodyToFlux(Skill.class)  // Expecting an array/list of skills
                .collectList()            // Convert Flux<Skill> -> List<Skill>
                .block();                 // block() for synchronous call
    }

}
