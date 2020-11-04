package com.TDD.ABnB.services.app_image_service;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.repositories.AppImageRepository;
import com.TDD.ABnB.repositories.AppInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppImageServiceImpl implements AppImageService{

    @Autowired
    private AppImageRepository appImageRepository;

    @Override
    public Iterable<AppImage> findAll() {
        return appImageRepository.findAll();
    }

    @Override
    public AppImage findById(Long id) {
        return appImageRepository.findById(id).get();
    }

    @Override
    public AppImage save(AppImage appImage) {
        return appImageRepository.save(appImage);
    }

    @Override
    public AppImage delete(Long id) {
        AppImage appImage = appImageRepository.findById(id).get();
        appImageRepository.delete(appImage);
        return appImage;
    }
}
