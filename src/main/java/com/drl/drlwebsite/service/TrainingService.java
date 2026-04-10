package com.drl.drlwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drl.drlwebsite.entity.Training;
import com.drl.drlwebsite.repository.TrainingRepository;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training updateTraining(Training training, Long id) {

        Training existing = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        // 🔥 delete old image if changed
        if (training.getImageUrl() != null &&
            existing.getImageUrl() != null &&
            !existing.getImageUrl().equals(training.getImageUrl())) {

            cloudinaryService.deleteFile(existing.getImageUrl());
        }

        existing.setTitle(training.getTitle());
        existing.setDescription(training.getDescription());
        existing.setDuration(training.getDuration());
        existing.setStartDate(training.getStartDate());
        existing.setEndDate(training.getEndDate());
        existing.setImageUrl(training.getImageUrl());
        existing.setCost(training.getCost());
        existing.setTrainer(training.getTrainer());

        return trainingRepository.save(existing);
    }

    public void deleteTraining(Long id) {

        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        // 🔥 delete image
        cloudinaryService.deleteFile(training.getImageUrl());

        trainingRepository.delete(training);
    }
}