package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.models.AppRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppInvoiceRepository extends PagingAndSortingRepository<AppInvoice,Long> {
}
