package com.TDD.ABnB.services.app_review_service;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.repositories.AppReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppReviewServiceImpl implements AppReviewService {

    @Autowired
    private AppReviewRepository appReviewRepository;


    @Override
    public Iterable<AppReview> findAll() {
        return appReviewRepository.findAll();
    }

    @Override
    public List<AppReview> findAllByAppProperty(AppProperty appProperty) {
        return appReviewRepository.findAllByAppProperty(appProperty);
    }


    @Override
    public AppReview findById(Long id) {
        return appReviewRepository.findById(id).get();
    }

    @Override
    public AppReview save(AppReview appReview) {
        return appReviewRepository.save(appReview);
    }

    @Override
    public void delete(AppReview appReview) {
            appReviewRepository.delete(appReview);
    }



}
