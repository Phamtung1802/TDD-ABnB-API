package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.services.app_role_service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/roles"})
public class AppRoleController {

    @Autowired
    private AppRoleService appRoleService;

    @GetMapping()
    public Iterable <AppRole> showListRole() {
        return appRoleService.findAll();
    }

    @GetMapping("/{id}")
    public AppRole showRole(@PathVariable("id") Long id) {
        return appRoleService.findById(id);
    }

    @PostMapping
    public AppRole createRole(@RequestBody AppRole appRole) {
        return appRoleService.save(appRole);
    }

    @PutMapping("/{id}")
    public AppRole updateRole(@PathVariable("id") Long id, @RequestBody AppRole appRole) {
        appRole.setId(id);
        return appRoleService.save(appRole);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        AppRole appRole = appRoleService.findById(id);
        appRoleService.remove(appRole);
    }
}
