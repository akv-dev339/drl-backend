package com.drl.drlwebsite.service;

import com.drl.drlwebsite.entity.Admin;
import com.drl.drlwebsite.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ Create Admin (Password Hashed)
    public Admin createAdmin(Admin admin) {

        // Check if username already exists
        if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Encode password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        return adminRepository.save(admin);
    }

    // ✅ Get All Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // ✅ Get Admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    // ✅ Update Admin (Optional password change)
    public Admin updateAdmin(Long id, Admin updatedAdmin) {

        Admin admin = getAdminById(id);

        admin.setUsername(updatedAdmin.getUsername());
        admin.setRole(updatedAdmin.getRole());

        // Only update password if provided
        if (updatedAdmin.getPassword() != null &&
                !updatedAdmin.getPassword().isBlank()) {

            admin.setPassword(
                    passwordEncoder.encode(updatedAdmin.getPassword())
            );
        }

        return adminRepository.save(admin);
    }

    // ✅ Delete Admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // ✅ Authenticate Admin (Used in Login)
    public Admin authenticate(String username, String rawPassword) {

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(rawPassword, admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return admin;
    }
}
