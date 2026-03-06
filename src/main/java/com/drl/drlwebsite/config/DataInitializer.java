package com.drl.drlwebsite.config;

import com.drl.drlwebsite.entity.Admin;
import com.drl.drlwebsite.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (adminRepository.count() == 0) {

            Admin admin = Admin.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ROLE_SUPER_ADMIN")
                    .build();

            adminRepository.save(admin);

            System.out.println("Default admin created: admin / admin123");
        }
    }
}
