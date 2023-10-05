package com.javaSchool.FinalTask3.controllers;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO What information do I specify in the HttpHeaders?
@RequestMapping(path = "countries")
@RequiredArgsConstructor
@RestController
public class CountryController {
    private final CountryService service;

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = service.getAllCountries();

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Country> getCountryById(@PathVariable String name) {
        Country country = service.getCountryById(name);

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Country> saveCountry(@RequestBody Country country) {
        Country savedCountry = service.saveCountry(country);

        if (savedCountry == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(service.saveCountry(country), HttpStatus.OK);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Country> updateCountry(@PathVariable String name, @RequestBody Country country){
        return new ResponseEntity<>(service.updateCountry(name, country), HttpStatus.OK);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Country> partiallyUpdateCountry(@PathVariable String name, @RequestBody Country country){
        return new ResponseEntity<>(service.partiallyUpdate(name, country), HttpStatus.OK);
    }

    // TODO What kind of ResponseEntity do I return if a country is deleted?
    @DeleteMapping("/{name}")
    public void deleteCountry(@PathVariable String name) {
        service.deleteCountry(name);
    }
}
