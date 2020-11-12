package com.TDD.ABnB.services.app_property_service;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppPropertyService {
    List<AppProperty> findAll();

    Iterable<AppProperty> findByAddress(String address);

    AppProperty findById(Long id);

    AppProperty save(AppProperty appProperty);

    void delete(AppProperty appProperty);

    List<AppProperty> findAllByAppUser(AppUser appUser);

}
