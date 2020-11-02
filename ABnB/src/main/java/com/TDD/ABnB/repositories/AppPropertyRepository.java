package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppInvoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppPropertyRepository extends PagingAndSortingRepository<AppInvoice,Long> {
}
