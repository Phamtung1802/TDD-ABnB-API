package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService{
    @Autowired
    AppUserRepository appUserRepository;

    public AppUser findFirstByName(String username){
       return appUserRepository.findFirstByName(username);
    }
}
