package com.TDD.ABnB.services.app_review_service;

import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.repositories.AppReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppReviewServiceImpl implements AppReviewService {

    @Autowired
    private AppReviewRepository appReviewRepository;

    @Override
    public Iterable<AppReview> findAll() {
        return appReviewRepository.findAll();
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
    public AppReview delete(Long id) {
          AppReview appReview = appReviewRepository.findById(id).get();
          appReviewRepository.delete(appReview);
          return appReview;
    }
}
