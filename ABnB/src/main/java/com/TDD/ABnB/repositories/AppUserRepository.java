package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
    public AppUser findFirstByName(String username);
    AppUser findByName(String username);

    AppUser findFirstByEmail(String email);

    AppUser findFirstByPhoneNumber(String phoneNumber);

    AppUser findFirstByPassword(String password);

    AppUser findFirstById (Long id);
}
