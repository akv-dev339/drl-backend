package com.drl.drlwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drl.drlwebsite.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}