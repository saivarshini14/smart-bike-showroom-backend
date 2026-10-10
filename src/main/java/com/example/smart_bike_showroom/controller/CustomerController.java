package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.entity.Customer;
import com.example.smart_bike_showroom.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {
        "https://smart-bike-showroom-frontend.onrender.com"
})
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(
            @RequestBody Customer customer) {

        Customer savedCustomer =
                customerService.registerCustomer(customer);

        savedCustomer.setPassword(null);

        return ResponseEntity.ok(savedCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> loginData) {

        String email = loginData.get("email");
        String password = loginData.get("password");

        var customer =
                customerService.loginCustomer(email, password);

        if (customer.isPresent()) {

            customer.get().setPassword(null);

            return ResponseEntity.ok(customer.get());

        } else {

            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(
                customerService.getAllCustomers()
        );
    }
}