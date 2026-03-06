package com.drl.drlwebsite.controller;

import com.drl.drlwebsite.entity.Volume;
import com.drl.drlwebsite.service.VolumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volumes")
@RequiredArgsConstructor
@CrossOrigin
public class VolumeController {

    private final VolumeService volumeService;

    // ✅ Create Volume under a Journal
    @PostMapping("/journal/{journalId}")
    public Volume createVolume(@PathVariable Long journalId,
                               @RequestBody Volume volume) {

        return volumeService.createVolume(journalId, volume);
    }

    // ✅ Get Volumes of a Journal
    @GetMapping("/journal/{journalId}")
    public List<Volume> getVolumesByJournal(@PathVariable Long journalId) {

        return volumeService.getVolumesByJournal(journalId);
    }

    // ✅ Get Volume by ID
    @GetMapping("/{id}")
    public Volume getVolumeById(@PathVariable Long id) {

        return volumeService.getVolumeById(id);
    }

    // ✅ Update Volume
    @PutMapping("/{id}")
    public Volume updateVolume(@PathVariable Long id,
                               @RequestBody Volume volume) {

        return volumeService.updateVolume(id, volume);
    }

    // ✅ Delete Volume
    @DeleteMapping("/{id}")
    public void deleteVolume(@PathVariable Long id) {

        volumeService.deleteVolume(id);
    }
}
