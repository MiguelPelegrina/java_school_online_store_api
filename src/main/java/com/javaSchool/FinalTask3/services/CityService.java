package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.CityDTO;
import com.javaSchool.FinalTask3.entities.City;
import com.javaSchool.FinalTask3.repositories.CityRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CityService {
    private final CityRepository repository;

    private final ModelMapper modelMapper;

    public List<CityDTO> getAllCities(){
        List<City> cities = repository.findAll();

        return cities.stream().map(city -> modelMapper.map(city, CityDTO.class)).collect(Collectors.toList());
    }

    public CityDTO getCityById(String name){
        City city = repository.findById(name).orElse(null);

        return modelMapper.map(city, CityDTO.class);
    }

    @Transactional
    public CityDTO saveCity(City city){
        City savedCity = repository.save(city);

        return modelMapper.map(savedCity, CityDTO.class);
    }

    @Transactional
    public CityDTO updateCity(String name, City city){
        City existingCity = repository.findById(name).orElse(null);

        // Check if the city exists
        if (existingCity != null){
            existingCity.setName(city.getName());
            existingCity.setActive(city.isActive());
            existingCity.setCountry(city.getCountry());

            City savedCity = repository.save(city);

            return modelMapper.map(savedCity, CityDTO.class);
        }

        return null;
    }

    @Transactional
    public CityDTO partiallyUpdateCity(String name, City updatedCity){
        City existingCity = repository.findById(name).orElse(null);

        // Check if the city exists
        if (existingCity != null){
            // Check updated attributes and replace old values with them
            if (updatedCity.getName() != null) {
                existingCity.setName(updatedCity.getName());
            }
            if (updatedCity.isActive()){
                existingCity.setActive(true);
            }
            if (updatedCity.getCountry() != null){
                existingCity.setCountry(updatedCity.getCountry());
            }

            City savedCity = repository.save(existingCity);

            return modelMapper.map(savedCity, CityDTO.class);
        }

        return null;
    }

    @Transactional
    public void deleteCity(String name){
        repository.deleteById(name);
    }
}
