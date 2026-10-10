
package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Bike;
import com.example.smart_bike_showroom.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class RecommendationService {

    private final BikeRepository bikeRepository;

    public RecommendationService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    private String text(String value) {
        return value == null
                ? ""
                : value.toLowerCase(Locale.ROOT).trim();
    }

    private boolean matchesCategory(String bikeCategory, String requested) {
        String category = text(bikeCategory);
        String wanted = text(requested);

        if (wanted.isEmpty()) {
            return true;
        }

        switch (wanted) {
            case "sports":
            case "sport":
                return category.contains("sport")
                        || category.contains("street");

            case "cruiser":
                return category.contains("cruiser")
                        || category.contains("touring");

            case "commuter":
                return category.contains("commuter")
                        || category.contains("street");

            default:
                return category.contains(wanted);
        }
    }

    private int scoreBike(
            Bike bike,
            Double mileage,
            Integer engine,
            String usage) {

        int score = 0;

        String category = text(bike.getCategory());
        String use = text(usage);

        if (mileage != null && mileage > 0
                && bike.getMileage() >= mileage) {
            score += 3;
        }

        if (engine != null && engine > 0
                && bike.getEngine() >= engine) {
            score += 2;
        }

        if (use.contains("daily") || use.contains("city")) {
            if (category.contains("commuter")
                    || category.contains("street")) {
                score += 3;
            }

            if (bike.getMileage() >= 40) {
                score += 2;
            }
        }

        if (use.contains("performance")) {
            if (category.contains("sport")
                    || category.contains("street")) {
                score += 3;
            }

            if (bike.getEngine() >= 150) {
                score += 2;
            }
        }

        if (use.contains("touring")) {
            if (category.contains("cruiser")
                    || category.contains("touring")) {
                score += 3;
            }

            if (bike.getEngine() >= 300) {
                score += 2;
            }
        }

        return score;
    }

    public List<Bike> recommendBikes(
            Double budget,
            String category,
            Double mileage,
            Integer engine,
            String usage) {

        return bikeRepository.findAll()
                .stream()

                // Never recommend bikes above the budget
                .filter(bike ->
                        budget == null
                                || (bike.getPrice() != null
                                && bike.getPrice() <= budget))

                // Match category keywords rather than exact text
                .filter(bike ->
                        matchesCategory(bike.getCategory(), category))

                // Best preference matches appear first
                .sorted(
                        Comparator.comparingInt(
                                (Bike bike) -> scoreBike(
                                        bike, mileage, engine, usage
                                )
                        ).reversed()
                        .thenComparing(Bike::getPrice)
                )

                .limit(3)
                .toList();
    }
}
