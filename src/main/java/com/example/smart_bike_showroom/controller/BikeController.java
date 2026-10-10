package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.entity.Bike;
import com.example.smart_bike_showroom.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
@CrossOrigin(origins = {
        "https://smart-bike-showroom-frontend.onrender.com"
})
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @PostMapping
    public Bike addBike(@RequestBody Bike bike) {
        return bikeService.addBike(bike);
    }

    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id) {
        return bikeService.getBikeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Bike updateBike(
            @PathVariable Long id,
            @RequestBody Bike bike) {

        return bikeService.updateBike(id, bike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable Long id) {

        bikeService.deleteBike(id);

        return ResponseEntity.ok("Bike deleted successfully");
    }
}