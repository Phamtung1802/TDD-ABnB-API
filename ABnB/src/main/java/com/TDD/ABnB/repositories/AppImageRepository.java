package com.TDD.ABnB.repositories;

import com.TDD.ABnB.models.AppImage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppImageRepository extends PagingAndSortingRepository<AppImage,Long> {
}
