package com.TDD.ABnB.controller;

import com.TDD.ABnB.exceptions.DuplilcateUserException;
import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_booking_service.AppBookingService;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class AppBookingController {

    @Autowired
    private AppBookingService appBookingService;
    @Autowired
    private AppPropertyService appPropertyServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;


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
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public ResponseEntity<AppBooking> createBooking(@RequestBody AppBooking appBooking) throws ParseException, DuplilcateUserException {
        Date checkinDate= new SimpleDateFormat("yyyy-MM-dd").parse(appBooking.getCheckinDate());
        Date checkoutDate= new SimpleDateFormat("yyyy-MM-dd").parse(appBooking.getCheckoutDate());
        Iterable<AppBooking> bookings= appBookingService.findAllByAppProperty(appPropertyServiceImpl.findById(appBooking.getAppProperty().getId()));
        for(AppBooking booking: bookings) {
            Date checkoutDateEx = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckoutDate());
            Date checkinDateEx = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckinDate());
            boolean bookingDateValid= checkinDate.before(checkoutDate);
            boolean checkinDateValid= checkinDate.after(checkoutDateEx);
            boolean checkOutDateValid= checkoutDate.before(checkinDateEx);
            System.out.println( bookingDateValid+" "+checkinDateValid + " "+checkinDateValid);
            if(!((bookingDateValid)&&((checkinDateValid)||(checkOutDateValid)))) {
                throw new DuplilcateUserException("Booking unavailable");
            }
        }
        AppBooking result=appBookingService.save(appBooking);
        ResponseEntity<AppBooking> res=new ResponseEntity<AppBooking>(result, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppBooking> updateBooking(@PathVariable("id") Long id, @RequestBody AppBooking appBooking) {
        appBooking.setId(id);
        ResponseEntity<AppBooking> res=new ResponseEntity<AppBooking>(appBooking, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public ResponseEntity<AppUser> deleteBooking(@PathVariable("id") Long id) throws DuplilcateUserException, ParseException {
        AppBooking appBooking =appBookingService.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkinDate= LocalDate.parse(appBooking.getCheckinDate(), formatter);
        Period period = Period.between(checkinDate, java.time.LocalDate.now());
        System.out.println(ChronoUnit.DAYS.between(checkinDate, java.time.LocalDate.now()));
        if(ChronoUnit.DAYS.between(checkinDate, java.time.LocalDate.now())>=-1) {
            throw new DuplilcateUserException("Cannot cancel booking this late");
        }
        AppUser user= appUserServiceImpl.findById(appBooking.getAppUser().getId());
        appBookingService.delete(appBooking);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(user,HttpStatus.ACCEPTED);
        return res;
    }
}
