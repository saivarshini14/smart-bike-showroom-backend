
package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.TestRide;
import com.example.smart_bike_showroom.repository.TestRideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestRideService {

    private static final double TEST_RIDE_FEE = 500.00;

    private final TestRideRepository testRideRepository;

    public TestRideService(TestRideRepository testRideRepository) {
        this.testRideRepository = testRideRepository;
    }

    // Book a test ride with a fixed fee of Rs. 500
    public TestRide bookTestRide(TestRide testRide) {
        testRide.setCost(TEST_RIDE_FEE);
        testRide.setStatus("BOOKED");
        return testRideRepository.save(testRide);
    }

    // Get all test rides
    public List<TestRide> getAllTestRides() {
        return testRideRepository.findAll();
    }

    // Get a test ride by ID
    public Optional<TestRide> getTestRideById(Long id) {
        return testRideRepository.findById(id);
    }

    // Get test rides by customer ID
    public List<TestRide> getByCustomer(Long customerId) {
        return testRideRepository.findByCustomerId(customerId);
    }

    // Get test rides by bike ID
    public List<TestRide> getByBike(Long bikeId) {
        return testRideRepository.findByBikeId(bikeId);
    }

    // Update an existing test ride
    public TestRide updateTestRide(Long id, TestRide details) {
        TestRide testRide = testRideRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Test ride not found"));

        testRide.setCustomerId(details.getCustomerId());
        testRide.setBikeId(details.getBikeId());
        testRide.setRideDate(details.getRideDate());
        testRide.setRideTime(details.getRideTime());
        testRide.setStatus(details.getStatus());

        // Keep the fee fixed even when a test ride is updated
        testRide.setCost(TEST_RIDE_FEE);

        return testRideRepository.save(testRide);
    }

    // Delete a test ride
    public void deleteTestRide(Long id) {
        testRideRepository.deleteById(id);
    }
}