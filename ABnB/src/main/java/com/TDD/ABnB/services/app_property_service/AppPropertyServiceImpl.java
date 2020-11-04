package com.TDD.ABnB.services.app_property_service;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.repositories.AppPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppPropertyServiceImpl implements AppPropertyService {

    @Autowired
    private AppPropertyRepository appPropertyRepository;

    @Override
    public Iterable<AppProperty> findAll() {
        return appPropertyRepository.findAll();
    }

    @Override
    public AppProperty findById(Long id) {
        return appPropertyRepository.findById(id).get();
    }

    @Override
    public AppProperty save(AppProperty appProperty) {
        return appPropertyRepository.save(appProperty);
    }

    public List<AppProperty> findAllByAddress(String address) {
        return appPropertyRepository.findAllByAddress(address);
    }

    @Override
    public AppProperty delete(Long id) {
        AppProperty appProperty = appPropertyRepository.findById(id).get();
        appPropertyRepository.delete(appProperty);
        return appProperty;
    }
}
