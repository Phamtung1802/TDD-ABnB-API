package com.TDD.ABnB.controller;


import com.TDD.ABnB.exceptions.DuplilcateUserException;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping()
    public ResponseEntity<Iterable<AppUser>> showListUser() {
        Iterable<AppUser> appReviews=appUserService.findAll();
        ResponseEntity<Iterable<AppUser>> res=new ResponseEntity<Iterable<AppUser>>(appReviews, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> showUser(@PathVariable("id") Long id) {
        AppUser appUser= appUserService.findById(id);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) throws Exception {
        try{
            appUserService.checkUserAvailability(appUser.getName());
        } catch (Exception e){
            throw e;
        }
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserService.save(appUser);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        appUserService.delete(appUser);
    }
}
