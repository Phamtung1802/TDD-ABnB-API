package com.TDD.ABnB.controller;


import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/users"})
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping()
    public ResponseEntity<AppUser> showUser(String name) {
        AppUser appUser = appUserService.findFirstByName(name);
        return new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> showUserById(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        return new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity <AppUser> createUser(@RequestBody AppUser appUser) {
        return new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AppUser> updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        appUserService.save(appUser);
        return new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUser>  deleteUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.delete(id);
        return new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
    }
}
