package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppInvoice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppReviewRepository extends PagingAndSortingRepository<AppInvoice,Long> {
}
