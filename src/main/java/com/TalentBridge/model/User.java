package com.TalentBridge.model;

import com.TalentBridge.utils.constants.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    @Column(name="userId", nullable = false, unique = true)
    private String userId;

    private String uuid;
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String phone;
    private String createdAt;
    private String updatedAt;
}
