package com.drl.drlwebsite.controller;

import com.drl.drlwebsite.entity.Admin;
import com.drl.drlwebsite.repository.AdminRepository;
import com.drl.drlwebsite.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Admin request) {

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // ✅ Compare encrypted password properly
        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // ✅ Now pass BOTH username and role
        String token = jwtUtil.generateToken(
                admin.getUsername(),
                admin.getRole()
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}
