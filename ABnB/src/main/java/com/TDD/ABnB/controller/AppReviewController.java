package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.services.app_review_service.AppReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class AppReviewController {

    @Autowired
    private AppReviewService appReviewService;

    @GetMapping()
    public ResponseEntity <Iterable<AppReview>> showListReview() {
        Iterable<AppReview> appReviews = appReviewService.findAll();
        return new ResponseEntity<>(appReviews, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppReview> showReview(@PathVariable("id") Long id) {
        AppReview appReview = appReviewService.findById(id);
         return new ResponseEntity<>(appReview, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity <AppReview> createReview(@RequestBody AppReview appReview) {
        appReviewService.save(appReview);
        return new ResponseEntity<>(appReview, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppReview> updateReview(@PathVariable("id") Long id, @RequestBody AppReview appReview) {
        appReview.setId(id);
        appReviewService.save(appReview);
        return new ResponseEntity<>(appReview, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppReview> deleteReview(@PathVariable("id") Long id) {
        AppReview appReview = appReviewService.delete(id);
        return new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
    }
}
