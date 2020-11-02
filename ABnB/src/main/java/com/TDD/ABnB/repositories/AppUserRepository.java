package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
}
