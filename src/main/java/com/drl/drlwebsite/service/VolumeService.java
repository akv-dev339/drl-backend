package com.drl.drlwebsite.service;

import com.drl.drlwebsite.entity.Journal;
import com.drl.drlwebsite.entity.Volume;
import com.drl.drlwebsite.repository.JournalRepository;
import com.drl.drlwebsite.repository.VolumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolumeService {

    private final VolumeRepository volumeRepository;
    private final JournalRepository journalRepository;

    public Volume createVolume(Long journalId, Volume volume) {

        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found"));

        volume.setJournal(journal);
        volume.setCreatedAt(LocalDateTime.now());

        // 🔥 AUTO MANAGE CURRENT FLAG
        if (volume.getIsCurrent()) {
            List<Volume> existingVolumes = volumeRepository.findByJournalId(journalId);

            for (Volume v : existingVolumes) {
                v.setIsCurrent(false);
            }

            volumeRepository.saveAll(existingVolumes);
        }

        return volumeRepository.save(volume);
    }

    public List<Volume> getVolumesByJournal(Long journalId) {
        return volumeRepository.findByJournalId(journalId);
    }

    public Volume getVolumeById(Long id) {
        return volumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volume not found"));
    }

    public Volume updateVolume(Long id, Volume updatedVolume) {

        Volume volume = getVolumeById(id);

        volume.setTitle(updatedVolume.getTitle());
        volume.setYear(updatedVolume.getYear());

        if (updatedVolume.getIsCurrent()) {
            List<Volume> volumes = volumeRepository.findByJournalId(
                    volume.getJournal().getId()
            );

            for (Volume v : volumes) {
                v.setIsCurrent(false);
            }

            volumeRepository.saveAll(volumes);
            volume.setIsCurrent(true);
        }

        return volumeRepository.save(volume);
    }

    public void deleteVolume(Long id) {
        volumeRepository.deleteById(id);
    }
}
