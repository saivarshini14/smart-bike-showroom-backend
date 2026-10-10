package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Customer;
import com.example.smart_bike_showroom.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Optional<Customer> loginCustomer(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);

        if (customer.isPresent()
                && passwordEncoder.matches(password, customer.get().getPassword())) {
            return customer;
        }

        return Optional.empty();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}