
package com.example.smart_bike_showroom.service;

import com.example.smart_bike_showroom.entity.Bike;
import com.example.smart_bike_showroom.entity.BikeBooking;
import com.example.smart_bike_showroom.repository.BikeBookingRepository;
import com.example.smart_bike_showroom.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeBookingService {

    private final BikeBookingRepository bookingRepository;
    private final BikeRepository bikeRepository;

    public BikeBookingService(
            BikeBookingRepository bookingRepository,
            BikeRepository bikeRepository) {
        this.bookingRepository = bookingRepository;
        this.bikeRepository = bikeRepository;
    }

    public BikeBooking createBooking(BikeBooking booking) {

        Bike bike = bikeRepository.findById(booking.getBikeId())
                .orElseThrow(() -> new RuntimeException("Bike not found"));

        if (bike.getPrice() == null) {
            throw new RuntimeException("Bike price is not available");
        }

        booking.setTotalPrice(bike.getPrice());
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    public List<BikeBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<BikeBooking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<BikeBooking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    public List<BikeBooking> getBookingsByBike(Long bikeId) {
        return bookingRepository.findByBikeId(bikeId);
    }

    public BikeBooking updateBooking(Long id, BikeBooking details) {

        BikeBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Bike bike = bikeRepository.findById(details.getBikeId())
                .orElseThrow(() -> new RuntimeException("Bike not found"));

        if (bike.getPrice() == null) {
            throw new RuntimeException("Bike price is not available");
        }

        booking.setCustomerId(details.getCustomerId());
        booking.setBikeId(details.getBikeId());
        booking.setBookingDate(details.getBookingDate());
        booking.setStatus(details.getStatus());
        booking.setTotalPrice(bike.getPrice());

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}