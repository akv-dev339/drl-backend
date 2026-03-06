package com.drl.drlwebsite.repository;

import com.drl.drlwebsite.entity.Volume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolumeRepository extends JpaRepository<Volume, Long> {

    List<Volume> findByJournalId(Long journalId);

    List<Volume> findByJournalIdAndIsCurrentTrue(Long journalId);
}
