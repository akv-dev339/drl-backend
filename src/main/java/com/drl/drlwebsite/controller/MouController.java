package com.drl.drlwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.drl.drlwebsite.entity.Mou;
import com.drl.drlwebsite.service.MouService;

@RestController
@RequestMapping("/api/lab/mou")
@CrossOrigin("*")
public class MouController {

    @Autowired
    private MouService mouService;

    // GET ALL
    @GetMapping
    public List<Mou> getAll() {
        return mouService.getAllMou();
    }

    // CREATE
    @PostMapping
    public Mou create(@RequestBody Mou mou) {
        return mouService.createMou(mou);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Mou update(@RequestBody Mou mou, @PathVariable Long id) {
        return mouService.updateMou(mou, id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mouService.deleteMou(id);
    }
}