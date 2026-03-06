package com.drl.drlwebsite.repository;

import com.drl.drlwebsite.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}