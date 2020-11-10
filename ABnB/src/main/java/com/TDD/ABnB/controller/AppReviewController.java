package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import com.TDD.ABnB.services.app_review_service.AppReviewService;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/review/{id}")
    public ResponseEntity<AppReview> showReview(@PathVariable("id") Long id) {
        AppReview appReview= appReviewService.findById(id);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<AppReview>> showHouseReview(@PathVariable("id") Long id) {
        AppProperty appProperty = appPropertyServiceImpl.findById(id);
        List<AppReview> appReviews = appReviewService.findAllByAppProperty(appProperty);
        ResponseEntity<List<AppReview>> res = new ResponseEntity<>(appReviews, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppReview> createReview(@RequestBody AppReview appReview) {
        AppUser appUser= appUserServiceImpl.findById(appReview.getAppUser().getId());
        AppProperty appProperty= appPropertyServiceImpl.findById(appReview.getAppProperty().getId());
        appReview.setAppProperty(appProperty);
        appReview.setAppUser(appUser);
//        appProperty.getAppReviews().add(appReview);
        appUser.getAppReviews().add(appReview);
//        appPropertyServiceImpl.save(appProperty);
        appUserServiceImpl.save(appUser);
        AppUser check=appUserServiceImpl.findById(appProperty.getAppUser().getId());
        appReview.getAppUser().setPassword(null);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return  res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppReview> updateReview(@PathVariable("id") Long id, @RequestBody AppReview appReview) {
        appReview.setId(id);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable("id") Long id) {
        AppReview appReview = appReviewService.findById(id);
        appReviewService.delete(appReview);
    }
}
