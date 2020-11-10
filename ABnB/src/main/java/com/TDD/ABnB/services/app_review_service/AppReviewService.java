package com.TDD.ABnB.services.app_review_service;

import com.TDD.ABnB.models.AppReview;
import org.springframework.stereotype.Service;

@Service
public interface AppReviewService {

    Iterable<AppReview> findAll();


    AppReview findById(Long id);

    AppReview save(AppReview appReview);

    void delete(AppReview appReview);


}
