package com.TalentBridge.service;

import com.TalentBridge.controller.ApplicationController;
import com.TalentBridge.wrapper.ApplicationRequest;
import com.TalentBridge.wrapper.utility.ApplicationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final WebClient.Builder webClientBuilder;

    private static final String APPLICATION_API_URL = "http://localhost:8082/api/applications";

    public ApplicationResponse createApplication(ApplicationRequest request) {
        WebClient client = webClientBuilder.build();

        // Forward request to external API and return its response
        return client.post()
                .uri(APPLICATION_API_URL + "/create")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApplicationResponse.class)
                .block();
    }
}