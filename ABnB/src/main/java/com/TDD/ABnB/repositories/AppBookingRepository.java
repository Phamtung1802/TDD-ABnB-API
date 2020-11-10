package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppBookingRepository extends PagingAndSortingRepository <AppBooking, Long> {
    List<AppBooking> findAllByAppUser(AppUser appUser);
}
