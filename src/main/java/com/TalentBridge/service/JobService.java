package com.TalentBridge.service;

import com.TalentBridge.wrapper.JobResponse;
import com.TalentBridge.wrapper.utility.JobRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {


    private final WebClient.Builder webClientBuilder;

    private static final String JOB_API_URL = "http://localhost:8082/api/jobs";

    public JobResponse createJob(JobRequest request) {
        WebClient client = webClientBuilder.build();

        return client.post()
                .uri(JOB_API_URL + "/create")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JobResponse.class)
                .block(); // block since controller expects sync response
    }

    public List<JobResponse> getAllJobs() {
        WebClient client = webClientBuilder.build();

        return client.get()
                .uri(JOB_API_URL + "/all")
                .retrieve()
                .bodyToMono(JobResponse[].class) // external API returns array
                .map(Arrays::asList)
                .block();
    }
}
