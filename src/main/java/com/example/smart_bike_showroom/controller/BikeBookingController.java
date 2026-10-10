package com.example.smart_bike_showroom.controller;

import com.example.smart_bike_showroom.entity.BikeBooking;
import com.example.smart_bike_showroom.service.BikeBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class BikeBookingController {

    private final BikeBookingService bookingService;

    public BikeBookingController(BikeBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BikeBooking createBooking(
            @RequestBody BikeBooking booking) {

        return bookingService.createBooking(booking);
    }

    @GetMapping
    public List<BikeBooking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeBooking> getBookingById(
            @PathVariable Long id) {

        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<BikeBooking> getBookingsByCustomer(
            @PathVariable Long customerId) {

        return bookingService.getBookingsByCustomer(customerId);
    }

    @GetMapping("/bike/{bikeId}")
    public List<BikeBooking> getBookingsByBike(
            @PathVariable Long bikeId) {

        return bookingService.getBookingsByBike(bikeId);
    }

    @PutMapping("/{id}")
    public BikeBooking updateBooking(
            @PathVariable Long id,
            @RequestBody BikeBooking booking) {

        return bookingService.updateBooking(id, booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.ok("Booking deleted successfully");
    }
}