package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppProperty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppBookingRepository extends PagingAndSortingRepository <AppBooking, Long> {
    Iterable<AppBooking> findAllByAppProperty(AppProperty appProperty);
}
