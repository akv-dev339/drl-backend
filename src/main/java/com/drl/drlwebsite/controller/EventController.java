package com.drl.drlwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drl.drlwebsite.entity.Events;
import com.drl.drlwebsite.service.EventService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



    @RestController
    @RequestMapping("/api/lab/events")
    @CrossOrigin("*")

public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping("/getall")
    public List<Events> getEvents() {
        return eventService.getAllEvents();
    }
    
    @PostMapping("/create")
    public Events createEvent(@RequestBody Events event) {
        //TODO: process POST request
        return eventService.createEvents(event);
    }
    
    @PutMapping("/{id}")
    public Events updateEvent(@RequestBody Events event, @PathVariable Long id) {
        //TODO: process PUT request
        return eventService.updateEvents(event, id);
    }
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

}
