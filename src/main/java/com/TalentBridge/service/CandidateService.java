package com.TalentBridge.service;

import com.TalentBridge.model.Candidate;
import com.TalentBridge.model.User;
import com.TalentBridge.repository.CandidateRepository;
import com.TalentBridge.repository.UserRepository;
import com.TalentBridge.utils.constants.EmpStatus;
import com.TalentBridge.utils.constants.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, UserRepository userRepository) {
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }

    public Candidate createCandidate(String userId, EmpStatus status) {
        // Fetch user
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Ensure role is CANDIDATE
        if (!Role.CANDIDATE.equals(user.getRole())) {
            throw new RuntimeException("User must have role CANDIDATE to become a Candidate.");
        }

        // Check if candidate already exists
        candidateRepository.findByCandId(user.getUserId()).ifPresent(c -> {
            throw new RuntimeException("Candidate already exists for user: " + userId);
        });

        Candidate candidate = new Candidate();
        candidate.setCandId(userId);  // Candidate PK is same as userId
//        candidate.setUser(user);
        candidate.setStatus(status);
//        candidate.setAppliedDate(String.valueOf(LocalDateTime.now()));
        candidate.setLastUpdated(String.valueOf(LocalDateTime.now()));

        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}

