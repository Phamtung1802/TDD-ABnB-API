package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppProperty;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class AppPropertyController {

    @Autowired
    private AppPropertyService appPropertyService;

    @GetMapping()
    public ResponseEntity <Iterable<AppProperty>> showListProperty() {
        Iterable<AppProperty> appProperties = appPropertyService.findAll();
        return new ResponseEntity<>(appProperties, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppProperty> showProperty(@PathVariable("id") Long id) {
        AppProperty appProperty = appPropertyService.findById(id);
        return new ResponseEntity<>(appProperty, HttpStatus.ACCEPTED);
    }


    @PostMapping()
    public ResponseEntity <AppProperty> createProperty(@RequestBody AppProperty appProperty) {
         appPropertyService.save(appProperty);
         return new ResponseEntity<>(appProperty, HttpStatus.ACCEPTED);

    }

    @PutMapping("/{id}")
    public ResponseEntity <AppProperty> updateProperty(@PathVariable("id") Long id, @RequestBody AppProperty appProperty) {
        appProperty.setId(id);
         appPropertyService.save(appProperty);
         return new ResponseEntity<>(appProperty, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppProperty> deleteProperty(@PathVariable("id") Long id ) {
        AppProperty appProperty = appPropertyService.delete(id);
        return new ResponseEntity<AppProperty>(appProperty, HttpStatus.ACCEPTED);
    }
}
