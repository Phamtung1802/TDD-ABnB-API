package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser findFirstByName(String name) {
        return appUserRepository.findFirstByName(name);
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public void remove(AppUser appUser) {
            appUserRepository.delete(appUser);
    }
}
