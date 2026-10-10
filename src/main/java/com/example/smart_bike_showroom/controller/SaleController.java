package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.entity.Sale;
import com.example.smart_bike_showroom.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(
            @PathVariable Long id) {

        return saleService.getSaleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<Sale> getSalesByCustomer(
            @PathVariable Long customerId) {

        return saleService.getSalesByCustomer(customerId);
    }

    @GetMapping("/bike/{bikeId}")
    public List<Sale> getSalesByBike(
            @PathVariable Long bikeId) {

        return saleService.getSalesByBike(bikeId);
    }

    @PutMapping("/{id}")
    public Sale updateSale(
            @PathVariable Long id,
            @RequestBody Sale sale) {

        return saleService.updateSale(id, sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(
            @PathVariable Long id) {

        saleService.deleteSale(id);

        return ResponseEntity.ok("Sale deleted successfully");
    }
}