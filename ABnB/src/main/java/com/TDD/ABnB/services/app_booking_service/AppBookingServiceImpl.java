package com.TDD.ABnB.services.app_booking_service;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.repositories.AppBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppBookingServiceImpl implements AppBookingService {

    @Autowired
    private AppBookingRepository appBookingRepository;

    @Override
    public Iterable<AppBooking> findAll() {
        return appBookingRepository.findAll();
    }

    @Override
    public AppBooking findById(Long id) {
        return appBookingRepository.findById(id).get();
    }

    @Override
    public AppBooking save(AppBooking appBooking) {
        return appBookingRepository.save(appBooking);
    }

    @Override
    public void delete(AppBooking appBooking) {
        appBookingRepository.delete(appBooking);
    }

    @Override
    public Iterable<AppBooking> findAllByAppProperty(AppProperty appProperty){
        return appBookingRepository.findAllByAppProperty(appProperty);
    }

}
