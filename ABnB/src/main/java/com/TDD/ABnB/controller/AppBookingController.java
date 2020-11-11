package com.TDD.ABnB.controller;

import com.TDD.ABnB.exceptions.DuplilcateUserException;
import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_booking_service.AppBookingService;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class AppBookingController {

    @Autowired
    private AppBookingService appBookingService;
    @Autowired
    private AppPropertyService appPropertyServiceImpl;

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
    public ResponseEntity<AppBooking> createBooking(@RequestBody AppBooking appBooking) throws ParseException, DuplilcateUserException {
        Date checkinDate= new SimpleDateFormat("yyyy-MM-dd").parse(appBooking.getCheckinDate());
        Date checkoutDate= new SimpleDateFormat("yyyy-MM-dd").parse(appBooking.getCheckoutDate());
        Iterable<AppBooking> bookings= appBookingService.findAllByAppProperty(appPropertyServiceImpl.findById(appBooking.getAppProperty().getId()));
        for(AppBooking booking: bookings) {
            Date checkoutDateEx = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckinDate());
            boolean bookingDateValid= checkinDate.after(checkoutDate);
            System.out.println("checkin");
            System.out.println(checkinDate);
            System.out.println("checkout");
            System.out.println(checkoutDateEx);
            boolean checkinDateValid= checkinDate.after(checkoutDateEx);
            System.out.println(checkinDateValid);
            if(!bookingDateValid && !checkinDateValid){
                throw new DuplilcateUserException("Booking unavailable");
            }
        }
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
