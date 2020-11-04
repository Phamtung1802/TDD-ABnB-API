package com.TDD.ABnB.services.app_role_service;

import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppRole findById(Long id) {
        return appRoleRepository.findById(id).get();
    }

    @Override
    public AppRole save(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppRole delete (Long id) {
        AppRole appRole = appRoleRepository.findById(id).get();
        appRoleRepository.delete(appRole);
        return appRole;

    }
}
