package com.lifestyle.service;

import com.lifestyle.dto.RegisterRequest;
import com.lifestyle.entity.Role;
import com.lifestyle.entity.User;
import com.lifestyle.util.HashUtil;
import org.springframework.stereotype.Service;

import com.lifestyle.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository users;
    public AuthService(UserRepository users){ this.users = users; }

    public User register(RegisterRequest req){
        users.findByEmail(req.getEmail()).ifPresent(u -> { throw new IllegalArgumentException("Email already exists"); });
        String salt = HashUtil.generateSaltHex(16);
        String hash = HashUtil.saltedHash(req.getPassword(), salt);

        User u = new User();
        u.setEmail(req.getEmail());
        u.setFullName(req.getFullName());
        u.setPasswordSalt(salt);
        u.setPasswordHash(hash);
        u.setRole(req.getRole() != null ? req.getRole() : Role.CUSTOMER);
        return users.save(u);
    }

    public User login(String email, String rawPassword){
        User u = users.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        String calc = HashUtil.saltedHash(rawPassword, u.getPasswordSalt());
        if(!calc.equals(u.getPasswordHash())) throw new IllegalArgumentException("Invalid credentials");
        return u;
    }
}