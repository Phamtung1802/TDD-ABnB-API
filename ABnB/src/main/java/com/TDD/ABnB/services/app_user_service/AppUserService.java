package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    public AppUser findFirstByName(String username);
    public AppUser findById(Long id);

    public AppUser save(AppUser appUser);

    public AppUser delete(Long id);



}
