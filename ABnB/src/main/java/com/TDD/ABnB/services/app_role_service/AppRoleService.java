package com.TDD.ABnB.services.app_role_service;

import com.TDD.ABnB.models.AppRole;
import org.springframework.stereotype.Service;

@Service
public interface AppRoleService {
    Iterable<AppRole> findAll();

    AppRole findById(Long id);

    AppRole save(AppRole appRole);

    void remove(AppRole appRole);
}
