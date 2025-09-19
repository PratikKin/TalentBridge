package com.TalentBridge.repository;

import com.TalentBridge.model.User;
import com.TalentBridge.utils.constants.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(String id);
    Optional<User> findTopByRoleOrderByUserIdDesc(Role role);
}

