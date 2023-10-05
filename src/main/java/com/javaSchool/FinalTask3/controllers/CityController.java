package com.javaSchool.FinalTask3.controllers;

import com.javaSchool.FinalTask3.dtos.CityDTO;
import com.javaSchool.FinalTask3.entities.City;
import com.javaSchool.FinalTask3.services.CityService;
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

@RequestMapping(path = "cities")
@RequiredArgsConstructor
@RestController
public class CityController {
    private final CityService service;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities(){
        List<CityDTO> cities = service.getAllCities();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable String name){
        CityDTO cityDTO = service.getCityById(name);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityDTO> saveCity(@RequestBody City city){
        CityDTO savedCity = service.saveCity(city);

        if (savedCity == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedCity, HttpStatus.OK);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable String name, @RequestBody City city){
        return new ResponseEntity<>(service.updateCity(name, city), HttpStatus.OK);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<CityDTO> partiallyUpdateCity(@PathVariable String name, @RequestBody City city){
        return new ResponseEntity<>(service.partiallyUpdateCity(name, city), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteCity(@PathVariable String name){
        service.deleteCity(name);
    }
}
