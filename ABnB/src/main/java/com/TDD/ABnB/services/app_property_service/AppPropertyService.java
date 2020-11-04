package com.TDD.ABnB.services.app_property_service;

import com.TDD.ABnB.models.AppProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppPropertyService {
    Iterable<AppProperty> findAll();

    AppProperty findById(Long id);

    AppProperty save(AppProperty appProperty);

    void remove(AppProperty appProperty);

}
