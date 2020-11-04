package com.TDD.ABnB.controller;


import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping()
    public ResponseEntity<Iterable<AppUser>> showListBooking() {
        Iterable<AppUser> appReviews=appUserService.findAll();
        ResponseEntity<Iterable<AppUser>> res=new ResponseEntity<Iterable<AppUser>>(appReviews, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> showBooking(@PathVariable("id") Long id) {
        AppUser appUser= appUserService.findById(id);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppUser> createBooking(@RequestBody AppUser appUser) {
        appUserService.save(appUser);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateBooking(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        appUserService.delete(appUser);
    }
}
