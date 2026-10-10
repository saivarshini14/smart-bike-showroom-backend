package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Admin;
import com.example.smart_bike_showroom.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Admin registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Optional<Admin> loginAdmin(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);

        if (admin.isPresent()
                && passwordEncoder.matches(password, admin.get().getPassword())) {
            return admin;
        }

        return Optional.empty();
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}