package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.services.app_image_service.AppImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/images"})
public class AppImageController {

    @Autowired
    private AppImageService appImageService;

    @GetMapping()
    public ResponseEntity <Iterable<AppImage>> showListImage() {
        Iterable<AppImage> appImages = appImageService.findAll();
       return new ResponseEntity<>(appImages, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppImage> showImage(@PathVariable("id") Long id) {
        AppImage appImage = appImageService.findById(id);
        return new ResponseEntity<>(appImage, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity <AppImage> createImage(@RequestBody AppImage appImage) {
         appImageService.save(appImage);
         return new ResponseEntity<>(appImage, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AppImage> updateImage(@PathVariable("id") Long id, @RequestBody AppImage appImage) {
         appImage.setId(id);
         appImageService.save(appImage);
         return new ResponseEntity<>(appImage, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppImage> deleteImage(@PathVariable("id") Long id) {
        AppImage appImage = appImageService.delete(id);
        return new ResponseEntity<AppImage>(appImage, HttpStatus.ACCEPTED);
    }
}
