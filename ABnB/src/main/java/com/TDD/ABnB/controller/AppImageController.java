package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.services.app_image_service.AppImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/images"})
public class AppImageController {

    @Autowired
    private AppImageService appImageService;

    @GetMapping()
    public Iterable<AppImage> showListImage() {
       return appImageService.findAll();
    }

    @GetMapping("/{id}")
    public AppImage showImage(@PathVariable("id") Long id) {
        return appImageService.findById(id);
    }

    @PostMapping()
    public AppImage createImage(@RequestBody AppImage appImage) {
        return appImageService.save(appImage);
    }

    @PutMapping("/{id}")
    public AppImage updateImage(@PathVariable("id") Long id, @RequestBody AppImage appImage) {
        appImage.setId(id);
        return appImageService.save(appImage);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        AppImage appImage = appImageService.findById(id);
        appImageService.remove(appImage);
    }
}
