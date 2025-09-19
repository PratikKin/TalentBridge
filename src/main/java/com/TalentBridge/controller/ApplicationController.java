package com.TalentBridge.controller;


import com.TalentBridge.model.Application;
import com.TalentBridge.service.ApplicationService;
import com.TalentBridge.wrapper.ApplicationRequest;
import com.TalentBridge.wrapper.utility.ApplicationResponse;
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
        ApplicationResponse response = applicationService.createApplication(request);
        return ResponseEntity.ok(response);
    }

}
