package com.TalentBridge.service;

import com.TalentBridge.model.Job;
import com.TalentBridge.model.Skill;
import com.TalentBridge.repository.JobRepository;
import com.TalentBridge.repository.SkillRepository;
import com.TalentBridge.wrapper.JobResponse;
import com.TalentBridge.wrapper.utility.JobRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;

    public Job createJob(JobRequest request) {
        Job job = new Job();

        // Generate sequential job ID
        String lastId = jobRepository.findLastJobId();
        int nextId = 1;
        if (lastId != null && lastId.startsWith("JOB_")) {
            try {
                nextId = Integer.parseInt(lastId.replace("JOB_", "")) + 1;
            } catch (NumberFormatException ignored) {}
        }
        job.setId("JOB_" + nextId);

        // Set job fields
        job.setCompanyName(request.getJob().getCompanyName());
        job.setTitle(request.getJob().getJobTitle());
        job.setQualification(request.getJob().getQualification());
        job.setLocation(request.getJob().getLocation());
        job.setSalary(request.getJob().getSalary());
        job.setExperience(request.getJob().getExperience());
        job.setDescription(request.getJob().getDescription());
        job.setPostedDate(request.getJob().getPostedDate());
        job.setLastUpdated(request.getJob().getPostedDate());

        // Resolve skills by ID from DB
        List<Skill> skills = request.getJob().getSkills().stream()
                .map(skillRef -> skillRepository.findById(skillRef.getId())
                        .orElseThrow(() -> new RuntimeException("Skill not found: " + skillRef.getId())))
                .toList();

        job.setSkills(skills);

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
