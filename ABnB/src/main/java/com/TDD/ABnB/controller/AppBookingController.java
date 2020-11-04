package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.services.app_booking_service.AppBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/bookings"})
public class AppBookingController {

    @Autowired
    private AppBookingService appBookingService;

    @GetMapping()
    public ResponseEntity <Iterable<AppBooking>> showListBooking() {
        Iterable<AppBooking> appBookings = appBookingService.findAll();
        return new ResponseEntity<>(appBookings, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppBooking> showBooking(@PathVariable("id") Long id) {
        AppBooking appBooking = appBookingService.findById(id);
        return new ResponseEntity<>(appBooking, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity <AppBooking> createBooking(@RequestBody AppBooking appBooking) {
         appBookingService.save(appBooking);
         return new ResponseEntity<>(appBooking, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AppBooking> updateBooking(@PathVariable("id") Long id, @RequestBody AppBooking appBooking) {
        appBooking.setId(id);
         appBookingService.save(appBooking);
         return new ResponseEntity<>(appBooking, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppBooking> deleteBooking(@PathVariable("id") Long id) {
        AppBooking appBooking = appBookingService.delete(id);
        return new ResponseEntity<AppBooking>(appBooking, HttpStatus.ACCEPTED);
    }
}
