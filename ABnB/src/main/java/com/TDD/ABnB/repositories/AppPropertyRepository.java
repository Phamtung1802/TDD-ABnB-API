package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppProperty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppPropertyRepository extends PagingAndSortingRepository<AppProperty,Long> {
    List<AppProperty> findAllByAddress(String address);

}
