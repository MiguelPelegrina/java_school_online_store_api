package com.javaSchool.FinalTask3.controllers;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping
    public List<Country> getAllEntities() {
        return service.getAllEntities();
    }

    @GetMapping("/{name}")
    public Country getEntityById(@PathVariable String name) {
        return service.getEntityById(name);
    }

    @PostMapping
    public Country saveEntity(@RequestBody Country entity) {
        return service.saveEntity(entity);
    }

    @DeleteMapping("/{name}")
    public void deleteEntity(@PathVariable String name) {
        service.deleteEntity(name);
    }
}
