package com.javaSchool.FinalTask3.controllers;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "countries")
@RequiredArgsConstructor
@RestController
public class CountryController {
    private final CountryService service;

    @GetMapping
    public List<Country> getAllCountries() {
        return service.getAllCountries();
    }

    @GetMapping("/{name}")
    public Country getCountryById(@PathVariable String name) {
        return service.getCountryById(name);
    }

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        return service.saveCountry(country);
    }

    @DeleteMapping("/{name}")
    public void deleteCountry(@PathVariable String name) {
        service.deleteCountry(name);
    }
}
