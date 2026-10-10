package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Payment;
import com.example.smart_bike_showroom.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        payment.setPaymentStatus("SUCCESS");

        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDate.now());
        }

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getByCustomer(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    public List<Payment> getByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public Payment updatePayment(Long id, Payment details) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setCustomerId(details.getCustomerId());
        payment.setBookingId(details.getBookingId());
        payment.setAmount(details.getAmount());
        payment.setPaymentMethod(details.getPaymentMethod());
        payment.setPaymentStatus(details.getPaymentStatus());
        payment.setPaymentDate(details.getPaymentDate());

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}