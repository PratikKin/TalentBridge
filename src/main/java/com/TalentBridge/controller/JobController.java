package com.TalentBridge.controller;

import com.TalentBridge.model.Job;
import com.TalentBridge.service.JobService;
import com.TalentBridge.utils.constants.JobStatus;
import com.TalentBridge.wrapper.JobResponse;
import com.TalentBridge.wrapper.utility.JobRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobController {

    private final JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest request) {
        if (!"RECRUITER".equalsIgnoreCase(String.valueOf(request.getRequestInfo().getRole()))) {
            System.out.println("Unauthorized attempt to create job by user with role: "
                    + request.getRequestInfo().getRole());
            return ResponseEntity.status(403).build(); // Forbidden
        }

        JobResponse response = jobService.createJob(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJobs() {

        return ResponseEntity.ok(jobService.getAllJobs());
    }
}
