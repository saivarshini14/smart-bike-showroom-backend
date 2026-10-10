package com.example.smart_bike_showroom.repository;

import com.example.smart_bike_showroom.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}