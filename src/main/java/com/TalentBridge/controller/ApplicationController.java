package com.TalentBridge.controller;


import com.TalentBridge.model.Application;
import com.TalentBridge.service.ApplicationService;
import com.TalentBridge.wrapper.ApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create")
    public ResponseEntity<?> createApplication(@RequestBody ApplicationRequest request) {
        Application application = applicationService.createApplication(request);

        return ResponseEntity.ok().body(
                new ApplicationResponse(application, "created")
        );
    }

    // Inner Response DTO
    record ApplicationResponse(Application application, String status) {}
}
