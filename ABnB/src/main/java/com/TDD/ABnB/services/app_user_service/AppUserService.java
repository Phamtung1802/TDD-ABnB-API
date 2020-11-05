package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.exceptions.DuplilcateUserException;
import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    AppUser findFirstByName(String name);

    AppUser findById(Long id);

    AppUser save(AppUser appUser);

    void delete (AppUser appUser);

    public Iterable<AppUser> findAll();

    public void checkUserAvailability(String name) throws DuplilcateUserException, Exception;

}
