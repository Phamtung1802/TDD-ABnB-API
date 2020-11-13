package com.TDD.ABnB.services.app_image_service;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.models.AppProperty;
import org.springframework.stereotype.Service;

@Service
public interface AppImageService {
    Iterable<AppImage> findAll();

    AppImage findById(Long id);

    AppImage save(AppImage appImage);

    void delete(Long  id);

    Iterable<AppImage> findAllByAppProperty(AppProperty appProperty);


}
