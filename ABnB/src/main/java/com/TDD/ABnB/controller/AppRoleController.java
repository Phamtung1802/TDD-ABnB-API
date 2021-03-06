package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.models.AppRole;
import com.TDD.ABnB.services.app_role_service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class AppRoleController {

    @Autowired
    private AppRoleService appRoleService;

    @GetMapping()
    public ResponseEntity<Iterable<AppRole>> showListRole() {
        Iterable<AppRole> appRoles=appRoleService.findAll();
        ResponseEntity<Iterable<AppRole>> res=new ResponseEntity<Iterable<AppRole>>(appRoles, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppRole> showRole(@PathVariable("id") Long id) {
        AppRole appRole= appRoleService.findById(id);
        ResponseEntity<AppRole> res=new ResponseEntity<AppRole>(appRole, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppRole> createRole(@RequestBody AppRole appRole) {
        appRoleService.save(appRole);
        ResponseEntity<AppRole> res=new ResponseEntity<AppRole>(appRole, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppRole> updateRole(@PathVariable("id") Long id, @RequestBody AppRole appRole) {
        appRole.setId(id);
        ResponseEntity<AppRole> res=new ResponseEntity<AppRole>(appRole, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        AppRole appRole = appRoleService.findById(id);
        appRoleService.delete(appRole);
    }
}
