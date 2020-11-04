package com.TDD.ABnB.controller;


import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping()
    public AppUser showUser(String name) {
        return appUserService.findFirstByName(name);
    }

    @GetMapping("/{id}")
    public AppUser showUserById(@PathVariable("id") Long id) {
        return appUserService.findById(id);
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser appUser) {
        return appUserService.save(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        return appUserService.save(appUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        appUserService.remove(appUser);
    }
}
