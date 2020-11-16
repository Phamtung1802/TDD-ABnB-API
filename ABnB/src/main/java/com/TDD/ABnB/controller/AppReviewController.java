package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import com.TDD.ABnB.services.app_review_service.AppReviewService;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class AppReviewController {

    @Autowired
    private AppReviewService appReviewService;

    @Autowired
    private AppUserService appUserServiceImpl;

    @Autowired
    private AppPropertyService appPropertyServiceImpl;


    @GetMapping()
    public ResponseEntity<Iterable<AppReview>> showListReview() {
        Iterable<AppReview> appReviews=appReviewService.findAll();
        ResponseEntity<Iterable<AppReview>> res=new ResponseEntity<Iterable<AppReview>>(appReviews, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppReview> showReview(@PathVariable("id") Long id) {
        AppReview appReview= appReviewService.findById(id);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public ResponseEntity<AppReview> createReview(@RequestBody AppReview appReview) {
        System.out.println("Reviews");
        AppUser appUser= appUserServiceImpl.findById(appReview.getAppUser().getId());
        AppProperty appProperty= appPropertyServiceImpl.findById(appReview.getAppProperty().getId());
        appReview.setAppProperty(appProperty);
        appReview.setAppUser(appUser);
        appReviewService.save(appReview);
        appUser.getAppReviews().add(appReview);
        appUserServiceImpl.save(appUser);
        appProperty.getAppReviews().add(appReview);
        appPropertyServiceImpl.save(appProperty);
        AppUser check=appUserServiceImpl.findById(appProperty.getAppUser().getId());
        appReview.getAppUser().setPassword(null);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return  res;
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public ResponseEntity<AppReview> updateReview(@PathVariable("id") Long id, @RequestBody AppReview appReview) {
        appReview.setId(id);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public void deleteReview(@PathVariable("id") Long id) {
        AppReview appReview = appReviewService.findById(id);
        appReviewService.delete(appReview);
    }
}
