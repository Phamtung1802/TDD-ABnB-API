package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
}
