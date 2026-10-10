package com.example.smart_bike_showroom.repository;

import com.example.smart_bike_showroom.entity.BikeBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeBookingRepository extends JpaRepository<BikeBooking, Long> {

    List<BikeBooking> findByCustomerId(Long customerId);

    List<BikeBooking> findByBikeId(Long bikeId);
}