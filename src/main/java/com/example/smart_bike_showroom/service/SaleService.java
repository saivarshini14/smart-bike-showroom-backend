package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Sale;
import com.example.smart_bike_showroom.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale createSale(Sale sale) {

        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDate.now());
        }

        if (sale.getPaymentStatus() == null) {
            sale.setPaymentStatus("PENDING");
        }

        if (sale.getSaleStatus() == null) {
            sale.setSaleStatus("PROCESSING");
        }

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public List<Sale> getSalesByCustomer(Long customerId) {
        return saleRepository.findByCustomerId(customerId);
    }

    public List<Sale> getSalesByBike(Long bikeId) {
        return saleRepository.findByBikeId(bikeId);
    }

    public Sale updateSale(Long id, Sale details) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        sale.setCustomerId(details.getCustomerId());
        sale.setBikeId(details.getBikeId());
        sale.setSaleDate(details.getSaleDate());
        sale.setAmount(details.getAmount());
        sale.setPaymentStatus(details.getPaymentStatus());
        sale.setSaleStatus(details.getSaleStatus());

        return saleRepository.save(sale);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}