package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.dto.RecommendationRequest;
import com.example.smart_bike_showroom.entity.Bike;
import com.example.smart_bike_showroom.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public List<Bike> recommendBikes(
            @RequestBody RecommendationRequest request) {

        return recommendationService.recommendBikes(
                request.getBudget(),
                request.getCategory(),
                request.getMileage(),
                request.getEngine(),
                request.getUsage()
        );
    }
}