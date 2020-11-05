package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.exceptions.DuplilcateUserException;
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
    public AppUser findById(Long id)   {
              return appUserRepository.findById(id).get();
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public void delete(AppUser appUser) {
            appUserRepository.delete(appUser);
    }

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public void checkUserAvailability(String name) throws DuplilcateUserException, Exception {
        if (appUserRepository.findByName(name) != null) {
            throw new DuplilcateUserException(" Ten nguoi dung ton tai");
        }
    }

    @Override
    public void checkEmailAvailability(String email) throws DuplilcateUserException, Exception  {
        if (appUserRepository.findByEmail(email) != null) {
            throw new DuplilcateUserException(" Email da ton tai");
        }
    }

    @Override
    public void checkPhoneAvailability(String phoneNumber) throws DuplilcateUserException, Exception {
        if (appUserRepository.findByPhoneNumber(phoneNumber) != null) {
            throw new DuplilcateUserException(" So dien thoai da ton tai");
        }
    }
}
