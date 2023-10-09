package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.CountryDTO;
import com.javaSchool.FinalTask3.entities.Country;
import com.javaSchool.FinalTask3.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable String name) {
        return new ResponseEntity<>(service.getInstanceById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CountryDTO> saveCountry(@RequestBody Country country) {
        CountryDTO savedCountry = service.saveInstance(country);

        if (savedCountry == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable String name, @RequestBody Country country){
        return new ResponseEntity<>(service.updateInstance(name, country), HttpStatus.OK);
    }

    // TODO What kind of ResponseEntity do I return if a country is deleted?
    @DeleteMapping("/{name}")
    public void deleteCountry(@PathVariable String name) {
        service.deleteInstance(name);
    }
}
