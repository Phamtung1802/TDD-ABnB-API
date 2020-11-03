package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.services.app_review_service.AppReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/reviews"})
public class AppReviewController {

    @Autowired
    private AppReviewService appReviewService;

    @GetMapping()
    public Iterable<AppReview> showListReview() {
        return appReviewService.findAll();
    }

    @GetMapping("/{id}")
    public AppReview showReview(@PathVariable("id") Long id) {
        return appReviewService.findById(id);
    }

    @PostMapping()
    public AppReview createReview(@RequestBody AppReview appReview) {
        return appReviewService.save(appReview);
    }

    @PutMapping("/{id}")
    public AppReview updateReview(@PathVariable("id") Long id, @RequestBody AppReview appReview) {
        appReview.setId(id);
        return appReviewService.save(appReview);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable("id") Long id) {
        AppReview appReview = appReviewService.findById(id);
        appReviewService.remove(appReview);
    }
}
