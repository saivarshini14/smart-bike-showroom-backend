package com.example.smart_bike_showroom.repository;

import com.example.smart_bike_showroom.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomerId(Long customerId);

    List<Sale> findByBikeId(Long bikeId);
}