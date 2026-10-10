package com.example.smart_bike_showroom.repository;

import com.example.smart_bike_showroom.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomerId(Long customerId);

    List<Payment> findByBookingId(Long bookingId);
}