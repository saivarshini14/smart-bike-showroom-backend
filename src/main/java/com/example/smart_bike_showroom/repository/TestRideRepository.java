package com.example.smart_bike_showroom.repository;

import com.example.smart_bike_showroom.entity.TestRide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRideRepository extends JpaRepository<TestRide, Long> {

    List<TestRide> findByCustomerId(Long customerId);

    List<TestRide> findByBikeId(Long bikeId);
}