package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/propertys"})
public class AppPropertyController {

    @Autowired
    private AppPropertyService appPropertyService;

    @GetMapping()
    public Iterable<AppProperty> showListProperty() {
        return appPropertyService.findAll();
    }

    @GetMapping("/{id}")
    public AppProperty showProperty(@PathVariable("id") Long id) {
        return appPropertyService.findById(id);
    }


    @PostMapping()
    public AppProperty createProperty(@RequestBody AppProperty appProperty) {
        return appPropertyService.save(appProperty);
    }

    @PutMapping("/{id}")
    public AppProperty updateProperty(@PathVariable("id") Long id, @RequestBody AppProperty appProperty) {
        appProperty.setId(id);
        return appPropertyService.save(appProperty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppProperty> deleteProperty(@PathVariable("id") Long id ) {
        AppProperty appProperty = appPropertyService.delete(id);
        return new ResponseEntity<AppProperty>(appProperty, HttpStatus.ACCEPTED);
    }
}
