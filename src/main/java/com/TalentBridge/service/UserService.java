package com.TalentBridge.service;
import com.TalentBridge.dto.UserDTO;
import com.TalentBridge.model.Candidate;
import com.TalentBridge.repository.CandidateRepository;
import com.TalentBridge.repository.UserRepository;
import com.TalentBridge.utils.constants.EmpStatus;
import com.TalentBridge.utils.constants.Role;
import com.TalentBridge.wrapper.UserRequest;
import com.TalentBridge.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final SupabaseAuthService supabaseAuthService;
    @Autowired
    private final CandidateRepository candidateRepository;



    public User registerUser(UserRequest request) {
        UserDTO user = request.getUser();
        String password = request.getPassword();

        // 1. Create user in Supabase
        String uuid = supabaseAuthService.createUserInSupabase(user.getEmail(), password);

        // 2. Generate business ID prefix based on role
        String prefix = Objects.equals(user.getRole(), Role.RECRUITER) ? "Rec_" : "Cand_";

        // 3. Find last ID for that role
        Optional<User> lastId = userRepository.findTopByRoleOrderByUserIdDesc(user.getRole());
        int nextNumber = (lastId.isPresent() && lastId.get().getUserId() != null)
                ? Integer.parseInt(lastId.get().getUserId().split("_")[1]) + 1
                : 1;
        String newUserId = prefix + nextNumber;

        // 4. Fill data into entity
        User userEntity = new User();
        userEntity.setUuid(uuid);
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole());
        userEntity.setPhone(user.getPhone());
        userEntity.setCreatedAt(Instant.now().toString());
        userEntity.setUpdatedAt(Instant.now().toString());
        userEntity.setUserId(newUserId);

        // 5. Save User
        User savedUser = userRepository.save(userEntity);

        // 6. If role == CANDIDATE, also save into Candidate table
        if (Role.CANDIDATE.equals(savedUser.getRole())) {
            Candidate candidate = new Candidate();
            candidate.setCandId(savedUser.getUserId()); // same ID
//            candidate.setUser(savedUser);
            candidate.setStatus(EmpStatus.UNEMPLOYED);
//            candidate.setAppliedDate(null);
            candidate.setLastUpdated(Instant.now().toString());

            candidateRepository.save(candidate);
        }

        return savedUser;
    }

}
