package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_booking_service.AppBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class AppBookingController {

    @Autowired
    private AppBookingService appBookingService;

    @GetMapping()
    public ResponseEntity<Iterable<AppBooking>> showListBooking() {
        Iterable<AppBooking> appBookings=appBookingService.findAll();
        ResponseEntity<Iterable<AppBooking>> res=new ResponseEntity<Iterable<AppBooking>>(appBookings, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppBooking> showBooking(@PathVariable("id") Long id) {
        AppBooking appBooking= appBookingService.findById(id);
        ResponseEntity<AppBooking> res=new ResponseEntity<AppBooking>(appBooking, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppBooking> createBooking(@RequestBody AppBooking appBooking) {
        appBookingService.save(appBooking);
        ResponseEntity<AppBooking> res=new ResponseEntity<AppBooking>(appBooking, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppBooking> updateBooking(@PathVariable("id") Long id, @RequestBody AppBooking appBooking) {
        appBooking.setId(id);
        ResponseEntity<AppBooking> res=new ResponseEntity<AppBooking>(appBooking, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") Long id) {
        AppBooking appBooking = appBookingService.findById(id);
        appBookingService.delete(appBooking);
    }
}
