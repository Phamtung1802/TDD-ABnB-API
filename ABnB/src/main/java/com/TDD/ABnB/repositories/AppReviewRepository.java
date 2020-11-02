package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppInvoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppReviewRepository extends PagingAndSortingRepository<AppInvoice,Long> {
}
