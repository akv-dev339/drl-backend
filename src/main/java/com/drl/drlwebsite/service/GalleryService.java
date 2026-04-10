package com.drl.drlwebsite.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drl.drlwebsite.entity.Gallery;
import com.drl.drlwebsite.repository.GalleryRepository;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    public List<Gallery> getByCategory(String category) {
        return galleryRepository.findByCategory(category);
    }

    public Gallery addImage(Gallery gallery) {
        gallery.setUploadedAt(LocalDateTime.now());
        return galleryRepository.save(gallery);
    }

    public void deleteImage(Long id) {

        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        // 🔥 delete file
        cloudinaryService.deleteFile(gallery.getImageUrl());

        galleryRepository.delete(gallery);
    }
}