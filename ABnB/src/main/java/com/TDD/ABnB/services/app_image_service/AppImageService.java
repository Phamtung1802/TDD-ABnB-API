package com.TDD.ABnB.services.app_image_service;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.models.AppInvoice;
import org.springframework.stereotype.Service;

@Service
public interface AppImageService {
    Iterable<AppImage> findAll();

    AppImage findById(Long id);

    AppImage save(AppImage appImage);

    AppImage delete(Long id);


}
