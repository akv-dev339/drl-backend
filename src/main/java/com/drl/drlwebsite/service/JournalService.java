package com.drl.drlwebsite.service;

import com.drl.drlwebsite.entity.Journal;
import com.drl.drlwebsite.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    public Journal createJournal(Journal journal) {
        journal.setCreatedAt(LocalDateTime.now());
        return journalRepository.save(journal);
    }

    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    public Journal getJournalById(Long id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found"));
    }

    public Journal updateJournal(Long id, Journal updatedJournal) {
        Journal journal = getJournalById(id);

        journal.setTitle(updatedJournal.getTitle());
        journal.setIssn(updatedJournal.getIssn());

        return journalRepository.save(journal);
    }

    public void deleteJournal(Long id) {
        journalRepository.deleteById(id);
    }
}
