package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.models.AppReview;
import com.TDD.ABnB.models.AppRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppReviewRepository extends PagingAndSortingRepository<AppReview,Long> {
}
