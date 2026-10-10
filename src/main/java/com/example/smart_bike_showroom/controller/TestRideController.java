package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.entity.TestRide;
import com.example.smart_bike_showroom.service.TestRideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-rides")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class TestRideController {

    private final TestRideService testRideService;

    public TestRideController(TestRideService testRideService) {
        this.testRideService = testRideService;
    }

    @PostMapping
    public TestRide bookTestRide(@RequestBody TestRide testRide) {
        return testRideService.bookTestRide(testRide);
    }

    @GetMapping
    public List<TestRide> getAllTestRides() {
        return testRideService.getAllTestRides();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestRide> getTestRideById(
            @PathVariable Long id) {

        return testRideService.getTestRideById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<TestRide> getByCustomer(
            @PathVariable Long customerId) {

        return testRideService.getByCustomer(customerId);
    }

    @GetMapping("/bike/{bikeId}")
    public List<TestRide> getByBike(
            @PathVariable Long bikeId) {

        return testRideService.getByBike(bikeId);
    }

    @PutMapping("/{id}")
    public TestRide updateTestRide(
            @PathVariable Long id,
            @RequestBody TestRide testRide) {

        return testRideService.updateTestRide(id, testRide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestRide(
            @PathVariable Long id) {

        testRideService.deleteTestRide(id);

        return ResponseEntity.ok("Test ride deleted successfully");
    }
}