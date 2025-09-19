package com.TalentBridge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SupabaseAuthService {

//    @Value("${supabase.url}")
    private String supabaseUrl = "https://kfynsuqvnhyumstcsplc.supabase.co";


//    @Value("${supabase.api.key}")
    private String supabaseApiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtmeW5zdXF2bmh5dW1zdGNzcGxjIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc1NzY1MjkyOCwiZXhwIjoyMDczMjI4OTI4fQ.8pfocsbPqx6SJnhwa17yLxEcptulhAYaVL6VVCb9jQo"; // must be service_role key



    private final RestTemplate restTemplate = new RestTemplate();

    public String createUserInSupabase(String email, String password) {

        System.out.println(supabaseApiKey+ supabaseUrl);
        String endpoint = supabaseUrl + "/auth/v1/admin/users"; // ✅ Admin endpoint

        String uuid = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", supabaseApiKey);
        headers.set("Authorization", "Bearer " + supabaseApiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(endpoint, request, Map.class);

            System.out.println("==== Supabase Request ====");
            System.out.println("POST " + endpoint);
            System.out.println("Headers: " + headers);
            System.out.println("Body: " + body);

            System.out.println("==== Supabase Response ====");
            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String id = (String) response.getBody().get("id");
                if (id != null) {
                    uuid=  id; // Supabase UUID
                }
            }else{

            throw new RuntimeException("Supabase signup failed: "
                    + response.getStatusCode() + " - " + response.getBody());}
        } catch (Exception e) {
            System.err.println("Supabase API call failed: " + e.getMessage());
            throw e;
        }
        return uuid;
    }

}
