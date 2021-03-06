package com.TDD.ABnB.services.app_booking_service;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppProperty;
import org.springframework.stereotype.Service;

@Service
public interface AppBookingService {
    Iterable<AppBooking> findAll();

    AppBooking findById(Long id);

    AppBooking save(AppBooking appBooking);

    void delete(AppBooking appBooking);

    Iterable<AppBooking> findAllByAppProperty(AppProperty appProperty);
}
