package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Bike;
import com.example.smart_bike_showroom.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Optional<Bike> getBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    public Bike updateBike(Long id, Bike bikeDetails) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bike not found"));

        bike.setBrand(bikeDetails.getBrand());
        bike.setModel(bikeDetails.getModel());
        bike.setPrice(bikeDetails.getPrice());
        bike.setMileage(bikeDetails.getMileage());
        bike.setEngine(bikeDetails.getEngine());
        bike.setFuelType(bikeDetails.getFuelType());
        bike.setCategory(bikeDetails.getCategory());
        bike.setDescription(bikeDetails.getDescription());
        bike.setImageUrl(bikeDetails.getImageUrl());

        return bikeRepository.save(bike);
    }

    public void deleteBike(Long id) {
        bikeRepository.deleteById(id);
    }
}