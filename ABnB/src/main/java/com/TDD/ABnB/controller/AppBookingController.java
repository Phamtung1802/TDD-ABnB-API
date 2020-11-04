package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.services.app_booking_service.AppBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/bookings"})
public class AppBookingController {

    @Autowired
    private AppBookingService appBookingService;

    @GetMapping()
    public Iterable<AppBooking> showListBooking() {
        return appBookingService.findAll();
    }

    @GetMapping("/{id}")
    public AppBooking showBooking(@PathVariable("id") Long id) {
        return appBookingService.findById(id);
    }

    @PostMapping()
    public AppBooking createBooking(@RequestBody AppBooking appBooking) {
        return appBookingService.save(appBooking);
    }

    @PutMapping("/{id}")
    public AppBooking updateBooking(@PathVariable("id") Long id, @RequestBody AppBooking appBooking) {
        appBooking.setId(id);
        return appBookingService.save(appBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") Long id) {
        AppBooking appBooking = appBookingService.findById(id);
        appBookingService.remove(appBooking);
    }
}
