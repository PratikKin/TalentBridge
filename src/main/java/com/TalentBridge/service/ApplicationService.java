package com.TalentBridge.service;

import com.TalentBridge.dto.ApplicationDTO;
import com.TalentBridge.dto.CandidateDTO;
import com.TalentBridge.dto.JobDTO;
import com.TalentBridge.model.Application;
import com.TalentBridge.model.Candidate;
import com.TalentBridge.model.Job;
import com.TalentBridge.repository.ApplicationRepository;
import com.TalentBridge.repository.CandidateRepository;
import com.TalentBridge.repository.JobRepository;
import com.TalentBridge.repository.UserRepository;
import com.TalentBridge.utils.constants.Role;
import com.TalentBridge.utils.constants.Status;
import com.TalentBridge.wrapper.ApplicationRequest;
import com.TalentBridge.wrapper.utility.ApplicationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,
                              CandidateRepository candidateRepository,
                              JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
    }

    public Application createApplication(ApplicationRequest request) {
        // ✅ Verify requester has role CANDIDATE
        Role requesterRole = request.getRequestInfo().getRole();
        String requesterId = request.getRequestInfo().getId();

        if (!Role.CANDIDATE.equals(requesterRole)) {
            throw new RuntimeException("Only candidates can apply for jobs.");
        }

        ApplicationBody appBody = request.getApplication();

        // ✅ Fetch candidate by candidateId
        Candidate candidate = candidateRepository.findByCandId(appBody.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + appBody.getCandidateId()));

        // ✅ Verify requesterId matches candidate
        if (!candidate.getCandId().equals(requesterId)) {
            System.out.println(candidate.getCandId()+ requesterId);
            throw new RuntimeException("You can only apply as your own candidate profile.");
        }

        // ✅ Fetch job by jobId
        Job job = jobRepository.findById(appBody.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found: " + appBody.getJobId()));

        // ✅ Build ApplicationDTO
        ApplicationDTO dto = new ApplicationDTO();
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setId(candidate.getCandId());
        candidateDTO.setStatus(candidate.getStatus());
//        candidateDTO.setAppliedDate(candidate.getAppliedDate());
        candidateDTO.setLastUpdated(candidate.getLastUpdated());

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompanyName(job.getCompanyName());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setQualification(job.getQualification());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setSalary(job.getSalary());
        jobDTO.setExperience(job.getExperience());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setPostedDate(job.getPostedDate());
        jobDTO.setLastUpdated(job.getLastUpdated());

        dto.setCandidateDTO(candidateDTO);
        dto.setJobDTO(jobDTO);
        dto.setStatus(Status.valueOf(appBody.getStatus()));
        dto.setAppliedDate(appBody.getAppliedDate() != null ? appBody.getAppliedDate() : String.valueOf(LocalDateTime.now()));
        dto.setLastUpdated(String.valueOf(LocalDateTime.now()));

        // ✅ Generate Application ID
        String newAppId = generateNewApplicationId();

        // ✅ Create Application entity
        Application application = new Application();
        application.setId(newAppId);
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(dto.getStatus());
        application.setAppliedDate(dto.getAppliedDate());
        application.setLastUpdated(dto.getLastUpdated());

        return applicationRepository.save(application);
    }

    private String generateNewApplicationId() {
        Optional<Application> lastApp = applicationRepository.findAll().stream()
                .max((a, b) -> {
                    int numA = Integer.parseInt(a.getId().replace("APP_", ""));
                    int numB = Integer.parseInt(b.getId().replace("APP_", ""));
                    return Integer.compare(numA, numB);
                });

        if (lastApp.isEmpty()) {
            return "APP_1";
        }

        String lastId = lastApp.get().getId(); // e.g., APP_12
        int num = Integer.parseInt(lastId.replace("APP_", ""));
        return "APP_" + (num + 1);
    }
}
