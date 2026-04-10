package com.drl.drlwebsite.controller;

import com.drl.drlwebsite.service.CloudinaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin("*")
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {
        return cloudinaryService.uploadFile(file);
    }
}