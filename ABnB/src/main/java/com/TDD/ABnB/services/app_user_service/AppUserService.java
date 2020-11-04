package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    AppUser findFirstByName(String name);

    AppUser findById(Long id);

    AppUser save(AppUser appUser);

    AppUser delete (Long id);
}
