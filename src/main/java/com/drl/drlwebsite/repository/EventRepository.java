package com.drl.drlwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drl.drlwebsite.entity.Events;

public interface EventRepository extends JpaRepository<Events, Long> {
}
