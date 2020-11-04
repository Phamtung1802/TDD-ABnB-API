package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.services.app_role_service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/roles"})
public class AppRoleController {

    @Autowired
    private AppRoleService appRoleService;

    @GetMapping()
    public ResponseEntity<Iterable<AppRole>> showListRole() {
        Iterable<AppRole> appRoles= appRoleService.findAll();
        return new ResponseEntity<>(appRoles, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppRole> showRole(@PathVariable("id") Long id) {
        AppRole appRole = appRoleService.findById(id);
        return new ResponseEntity<AppRole>(appRole, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity <AppRole> createRole(@RequestBody AppRole appRole) {
        return new ResponseEntity<>(appRole, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppRole> updateRole(@PathVariable("id") Long id, @RequestBody AppRole appRole) {
        appRole.setId(id);
        appRoleService.save(appRole);
        return new ResponseEntity<>(appRole, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppRole> deleteRole(@PathVariable("id") Long id) {
        AppRole appRole = appRoleService.delete(id);
        return new ResponseEntity<AppRole>(appRole, HttpStatus.ACCEPTED);
    }
}
