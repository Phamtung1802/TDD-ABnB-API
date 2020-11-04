package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppReview;
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
    public ResponseEntity<AppReview> createReview(@RequestBody AppReview appReview) {
        appReviewService.save(appReview);
        ResponseEntity<AppReview> res=new ResponseEntity<AppReview>(appReview, HttpStatus.ACCEPTED);
        return res;
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
