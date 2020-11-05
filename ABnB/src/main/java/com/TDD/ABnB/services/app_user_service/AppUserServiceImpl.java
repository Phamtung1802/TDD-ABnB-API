package com.TDD.ABnB.services.app_user_service;

import com.TDD.ABnB.exceptions.DuplilcateUserException;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

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
    public String checkUserAvailability(String name) {
        if (appUserRepository.findFirstByName(name) != null) {
            return "Username not available";
        }
        return null;
    }

    @Override
    public String checkEmailAvailability(String email) {
        if (appUserRepository.findFirstByEmail(email)!= null) {
            return "Email already registered";
        }
        return null;
    }

    @Override
    public String checkPhoneAvailability(String phoneNumber) {
        if (appUserRepository.findFirstByPhoneNumber(phoneNumber) != null) {
            return "Phone Number already registered";
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= appUserRepository.findFirstByName(username);
        if (appUser!=null) {
            return User.withUsername(appUser.getName()).password(appUser.getPassword()).roles(appUser.getAppRole().getName()).build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
