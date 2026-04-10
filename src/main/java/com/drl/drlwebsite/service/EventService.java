package com.drl.drlwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drl.drlwebsite.entity.Events;
import com.drl.drlwebsite.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Events> getAllEvents(){
        return eventRepository.findAll();
    }

    public Events createEvents(Events event){
        return eventRepository.save(event);
    }

    public Events updateEvents(Events event, Long id){

        Events existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // 🔥 delete old image if replaced
        if (event.getImageUrl() != null &&
            existingEvent.getImageUrl() != null &&
            !existingEvent.getImageUrl().equals(event.getImageUrl())) {

            cloudinaryService.deleteFile(existingEvent.getImageUrl());
        }

        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setImageUrl(event.getImageUrl());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id){

        Events event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // 🔥 delete image
        cloudinaryService.deleteFile(event.getImageUrl());

        eventRepository.delete(event);
    }
}