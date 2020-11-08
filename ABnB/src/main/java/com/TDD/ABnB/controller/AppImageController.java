package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppBooking;
import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.services.app_image_service.AppImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class AppImageController {

    @Autowired
    private AppImageService appImageService;

    @GetMapping()
    public ResponseEntity<Iterable<AppImage>> showListImage() {
        Iterable<AppImage> appImages=appImageService.findAll();
        ResponseEntity<Iterable<AppImage>> res=new ResponseEntity<Iterable<AppImage>>(appImages, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppImage> showImage(@PathVariable("id") Long id) {
        AppImage appImage= appImageService.findById(id);
        ResponseEntity<AppImage> res=new ResponseEntity<AppImage>(appImage, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppImage> createImage(@RequestBody AppImage appImage) {
        appImageService.save(appImage);
        ResponseEntity<AppImage> res=new ResponseEntity<AppImage>(appImage, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppImage> updateImage(@PathVariable("id") Long id, @RequestBody AppImage appImage) {
        appImage.setId(id);
        ResponseEntity<AppImage> res=new ResponseEntity<AppImage>(appImage, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        AppImage appImage = appImageService.findById(id);
        appImageService.delete(appImage);
    }
}
