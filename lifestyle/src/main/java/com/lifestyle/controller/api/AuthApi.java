package com.lifestyle.controller.api;

import com.lifestyle.dto.RegisterRequest;
import com.lifestyle.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lifestyle.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
    private final AuthService auth;
    public AuthApi(AuthService auth){ this.auth = auth; }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest req){
        return ResponseEntity.ok(auth.register(req));
    }
}