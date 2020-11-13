package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.services.app_image_service.AppImageService;
import com.TDD.ABnB.services.app_property_service.AppPropertyService;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class AppImageController {

    @Autowired
    private AppImageService appImageService;

    @Autowired
    private AppPropertyService appPropertyService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping()
    public ResponseEntity<Iterable<AppImage>> showListImage() {
        Iterable<AppImage> appImages=appImageService.findAll();
        if (appImages == null) {
            return new ResponseEntity<Iterable<AppImage>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<AppImage>>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppImage> showImage(@PathVariable("id") Long id) {
        AppImage appImage= appImageService.findById(id);
        if (appImage == null) {
            return new ResponseEntity<AppImage>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AppImage>(HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Void> createImage(@RequestBody AppImage appImage, UriComponentsBuilder uriBuilder) {
        appImageService.save(appImage);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/images/{id}").buildAndExpand(appImage.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppImage> updateImage(@PathVariable("id") Long id, @RequestBody AppImage appImage) {
      appImageService.findById(id);
      if (appImage == null) {
          return new ResponseEntity<AppImage>(HttpStatus.NOT_FOUND);
      }
      appImage.setName(appImage.getName());
      return new ResponseEntity<AppImage>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppImage> deleteImage(@PathVariable("id") Long id) {
        AppImage appImage = appImageService.findById(id);
        if (appImage == null) {
            return new ResponseEntity<AppImage>(HttpStatus.NOT_FOUND);
        }
        appImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
