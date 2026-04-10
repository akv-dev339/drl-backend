package com.drl.drlwebsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drl.drlwebsite.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    // Filter by category
    List<Gallery> findByCategory(String category);
}