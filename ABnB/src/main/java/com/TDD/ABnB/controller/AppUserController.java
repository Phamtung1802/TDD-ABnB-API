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
@CrossOrigin("*")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping()
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_RENTER"})
    public ResponseEntity<Iterable<AppUser>> showListUser() {
        Iterable<AppUser> appReviews = appUserService.findAll();
        ResponseEntity<Iterable<AppUser>> res = new ResponseEntity<Iterable<AppUser>>(appReviews, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> showUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) throws Exception {
        String userCheck = appUserService.checkUserAvailability(appUser.getName());
        String emailCheck = appUserService.checkEmailAvailability(appUser.getEmail());
        String phoneNumberCheck = appUserService.checkPhoneAvailability(appUser.getPhoneNumber());
        StringBuilder errorMessage = new StringBuilder();
        if (userCheck != null) {
            errorMessage.append(userCheck + "<br>");
        }
        if (emailCheck != null) {
            errorMessage.append(emailCheck + "<br>");
        }
        if (phoneNumberCheck != null) {
            errorMessage.append(phoneNumberCheck + "<br>");
        }
        if (errorMessage != null) {
            throw new DuplilcateUserException(errorMessage.toString());
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserService.save(appUser);
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        appUserService.delete(appUser);
    }
}
