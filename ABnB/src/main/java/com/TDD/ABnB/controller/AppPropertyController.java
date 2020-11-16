package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_image_service.AppImageService;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/property")
@CrossOrigin("*")
public class AppPropertyController {

    @Autowired
    private AppPropertyService appPropertyService;

    @Autowired
    private AppUserService appUserServiceImpl;

    @Autowired
    private AppImageService appImageServiceImpl;



    @GetMapping()
    public ResponseEntity<Iterable<AppProperty>> showListProperty() {
        Iterable<AppProperty> appProperties=appPropertyService.findAll();
        ResponseEntity<Iterable<AppProperty>> res=new ResponseEntity<Iterable<AppProperty>>(appProperties, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<AppProperty> showProperty(@PathVariable("id") Long id) {
        AppProperty appProperty= appPropertyService.findById(id);
        ResponseEntity<AppProperty> res=new ResponseEntity<AppProperty>(appProperty, HttpStatus.ACCEPTED);
        return res;

    }

//    tinh nang da xong
//    @PostMapping()
//    public ResponseEntity<AppProperty> createProperty(@RequestBody AppProperty appProperty) {
//        appPropertyService.save(appProperty);
//        ResponseEntity<AppProperty> res=new ResponseEntity<AppProperty>(appProperty, HttpStatus.ACCEPTED);
//        System.out.println(appUserServiceImpl.findById(appProperty.getAppUser().getId()).getAppProperties());
//        return res;
//    }as

    @PostMapping()
    public ResponseEntity<AppUser> createProperty(@RequestBody AppProperty appProperty) {
        AppUser appUser= appUserServiceImpl.findById(appProperty.getAppUser().getId());
        ArrayList<AppImage> images=new ArrayList<AppImage>();
        appProperty.setAppUser(appUser);
        appProperty = appPropertyService.save(appProperty);
        for (AppImage image: appProperty.getAppImages()
        ) {
            image.setAppProperty(appProperty);
            appImageServiceImpl.save(image);
        }
        appUser.getAppProperties().add(appProperty);
        appUserServiceImpl.save(appUser);
        appProperty.getAppUser().setPassword(null);
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping("/find-by-address")
    public ResponseEntity<AppUser> findByAdress(@RequestBody AppProperty appProperty) {
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(new AppUser(), HttpStatus.ACCEPTED);
        return res;
    }


    @PatchMapping()
    public ResponseEntity<AppUser> updatePropertyStatus(@RequestBody AppProperty appProperty) {
        AppProperty appProperty1= appPropertyService.findById(appProperty.getId());
        System.out.println(appProperty1.getName());
        System.out.println(appProperty.getStatus());
        appProperty1.setStatus(appProperty.getStatus());
        appPropertyService.save(appProperty1);
        AppUser appUser= appUserServiceImpl.findById(appProperty1.getAppUser().getId());
        ResponseEntity<AppUser> res=new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }


    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable("id") Long id ) {
        AppProperty appProperty = appPropertyService.findById(id);
        appPropertyService.delete(appProperty);
    }
}
