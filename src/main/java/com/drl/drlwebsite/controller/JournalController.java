package com.drl.drlwebsite.controller;

import com.drl.drlwebsite.entity.Journal;
import com.drl.drlwebsite.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
@RequiredArgsConstructor
@CrossOrigin
public class JournalController {

    private final JournalService journalService;

    @PostMapping
    public Journal createJournal(@RequestBody Journal journal) {
        return journalService.createJournal(journal);
    }

    @GetMapping
    public List<Journal> getAllJournals() {
        return journalService.getAllJournals();
    }

    @GetMapping("/{id}")
    public Journal getJournalById(@PathVariable Long id) {
        return journalService.getJournalById(id);
    }

    @PutMapping("/{id}")
    public Journal updateJournal(@PathVariable Long id,
                                 @RequestBody Journal journal) {
        return journalService.updateJournal(id, journal);
    }

    @DeleteMapping("/{id}")
    public void deleteJournal(@PathVariable Long id) {
        journalService.deleteJournal(id);
    }
}
