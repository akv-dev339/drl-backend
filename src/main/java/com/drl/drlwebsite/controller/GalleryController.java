package com.drl.drlwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.drl.drlwebsite.entity.Gallery;
import com.drl.drlwebsite.service.GalleryService;

@RestController
@RequestMapping("/api/lab/gallery")
@CrossOrigin("*")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    // GET ALL
    @GetMapping
    public List<Gallery> getAll() {
        return galleryService.getAllImages();
    }

    // GET BY CATEGORY
    @GetMapping("/category")
    public List<Gallery> getByCategory(@RequestParam String category) {
        return galleryService.getByCategory(category);
    }

    // CREATE
    @PostMapping
    public Gallery create(@RequestBody Gallery gallery) {
        return galleryService.addImage(gallery);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        galleryService.deleteImage(id);
    }
}