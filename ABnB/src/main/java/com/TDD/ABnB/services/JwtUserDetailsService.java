package com.TDD.ABnB.services;

import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserService appUserServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<AppRole> role= new ArrayList<AppRole>();
        if (appUserServiceImpl.findFirstByName(username)!=null) {
            role.add(appUserServiceImpl.findFirstByName(username).getAppRole());
            return new User(username, appUserServiceImpl.findFirstByName(username).getPassword(),role);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}