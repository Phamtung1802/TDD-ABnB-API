package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppPropertyRepository extends PagingAndSortingRepository<AppProperty,Long> {

    List<AppProperty> findAllByAddressContaining(String address);

    List<AppProperty> findAllByAppUser(AppUser appUser);

    List<AppProperty> findAll();

}
