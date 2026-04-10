package com.drl.drlwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drl.drlwebsite.entity.Mou;
import com.drl.drlwebsite.repository.MouRepository;

@Service
public class MouService {

    @Autowired
    private MouRepository mouRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Mou> getAllMou() {
        return mouRepository.findAll();
    }

    public Mou createMou(Mou mou) {
        return mouRepository.save(mou);
    }

    public Mou updateMou(Mou mou, Long id) {

        Mou existing = mouRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MoU not found"));

        // 🔥 delete old logo
        if (mou.getLogoUrl() != null &&
            existing.getLogoUrl() != null &&
            !existing.getLogoUrl().equals(mou.getLogoUrl())) {

            cloudinaryService.deleteFile(existing.getLogoUrl());
        }

        // 🔥 delete old document
        if (mou.getDocumentUrl() != null &&
            existing.getDocumentUrl() != null &&
            !existing.getDocumentUrl().equals(mou.getDocumentUrl())) {

            cloudinaryService.deleteFile(existing.getDocumentUrl());
        }

        existing.setOrganizationName(mou.getOrganizationName());
        existing.setDescription(mou.getDescription());
        existing.setLogoUrl(mou.getLogoUrl());
        existing.setDateSigned(mou.getDateSigned());
        existing.setDocumentUrl(mou.getDocumentUrl());

        return mouRepository.save(existing);
    }

    public void deleteMou(Long id) {

        Mou mou = mouRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MoU not found"));

        // 🔥 delete both files
        cloudinaryService.deleteFile(mou.getLogoUrl());
        cloudinaryService.deleteFile(mou.getDocumentUrl());

        mouRepository.delete(mou);
    }
}