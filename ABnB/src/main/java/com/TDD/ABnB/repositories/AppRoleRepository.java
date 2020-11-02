package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppRoleRepository extends PagingAndSortingRepository<AppRole,Long> {
}
