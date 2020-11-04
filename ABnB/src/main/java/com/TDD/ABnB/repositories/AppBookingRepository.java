package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppBooking;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppBookingRepository extends PagingAndSortingRepository <AppBooking, Long> {
}
