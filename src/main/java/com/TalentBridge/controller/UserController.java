package com.TalentBridge.controller;

import com.TalentBridge.model.User;
import com.TalentBridge.service.UserService;
import com.TalentBridge.wrapper.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest request) {
        User savedUser = userService.registerUser(request);
        return ResponseEntity.ok(savedUser);
    }
}

