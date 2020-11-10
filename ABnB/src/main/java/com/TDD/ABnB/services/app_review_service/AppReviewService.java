package com.TDD.ABnB.services.app_review_service;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppReviewService {

    Iterable<AppReview> findAll();

    List<AppReview> findAllByAppProperty(AppProperty appProperty);


    AppReview findById(Long id);

    AppReview save(AppReview appReview);

    void delete(AppReview appReview);


}
