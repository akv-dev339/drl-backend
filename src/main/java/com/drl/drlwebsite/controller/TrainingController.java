package com.drl.drlwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.drl.drlwebsite.entity.Training;
import com.drl.drlwebsite.service.TrainingService;

@RestController
@RequestMapping("/api/lab/training")
@CrossOrigin("*")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    // GET ALL
    @GetMapping
    public List<Training> getAll() {
        return trainingService.getAllTrainings();
    }

    // CREATE
    @PostMapping
    public Training create(@RequestBody Training training) {
        return trainingService.createTraining(training);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Training update(@RequestBody Training training, @PathVariable Long id) {
        return trainingService.updateTraining(training, id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trainingService.deleteTraining(id);
    }
}