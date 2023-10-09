package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.CityDTO;
import com.javaSchool.FinalTask3.entities.City;
import com.javaSchool.FinalTask3.services.CityService;
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

@RequestMapping(path = "cities")
@RequiredArgsConstructor
@RestController
public class CityController {
    private final CityService service;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable String name){
        return new ResponseEntity<>(service.getInstanceById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityDTO> saveCity(@RequestBody City city){
        CityDTO savedCity = service.saveInstance(city);

        if (savedCity == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable String name, @RequestBody City city){
        return new ResponseEntity<>(service.updateInstance(name, city), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteCity(@PathVariable String name){
        service.deleteInstance(name);
    }
}
