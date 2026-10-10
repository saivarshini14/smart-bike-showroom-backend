package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.entity.Admin;
import com.example.smart_bike_showroom.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {
        "https://smart-bike-showroom-frontend.onrender.com"
})
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> register(
            @RequestBody Admin admin) {

        Admin savedAdmin = adminService.registerAdmin(admin);

        savedAdmin.setPassword(null);

        return ResponseEntity.ok(savedAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> loginData) {

        String email = loginData.get("email");
        String password = loginData.get("password");

        var admin = adminService.loginAdmin(email, password);

        if (admin.isPresent()) {

            admin.get().setPassword(null);

            return ResponseEntity.ok(admin.get());

        } else {

            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(
                adminService.getAllAdmins()
        );
    }
}