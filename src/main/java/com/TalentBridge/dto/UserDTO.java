package com.TalentBridge.dto;

import com.TalentBridge.utils.constants.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String Id = null; // from backend
    private String uuid;  // from supabase
    private String name;  // from supabase|ui
    private String email; // from supabase|ui
    @Enumerated(EnumType.STRING)
    private Role role;    // "candidate" or "recruiter"
    private String phone; // optional
    private String createdAt = null;
    private String updatedAt = null;
}