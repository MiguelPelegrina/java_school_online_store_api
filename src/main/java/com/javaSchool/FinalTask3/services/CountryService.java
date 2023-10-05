package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.CountryDTO;
import com.javaSchool.FinalTask3.entities.Country;
import com.javaSchool.FinalTask3.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// TODO Not sure if I should try to implement the Spring transaction routing mentioned in
// https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository repository;

    private final ModelMapper modelMapper;

    public List<CountryDTO> getAllCountries() {
        List<Country> countries = repository.findAll();

        return countries.stream().map(country -> modelMapper.map(country, CountryDTO.class)).collect(Collectors.toList());
    }

    public CountryDTO getCountryById(String name) {
        Country country = repository.findById(name).orElse(null);

        return modelMapper.map(country, CountryDTO.class);
    }

    @Transactional
    public CountryDTO saveCountry(Country country) {
        Country savedCountry = repository.save(country);

        return modelMapper.map(savedCountry, CountryDTO.class);
    }

    @Transactional
    public CountryDTO updateCountry(String name, Country country) {
        Country existingCountry = repository.findById(name).orElse(null);

        // Check if the country exists
        if (existingCountry != null){
            existingCountry.setName(country.getName());
            existingCountry.setActive(country.isActive());

            Country savedCountry = repository.save(existingCountry);

            return modelMapper.map(savedCountry, CountryDTO.class);
        }

        return null;
    }

    @Transactional
    public CountryDTO partiallyUpdateCountry(String name, Country updatedCountry){
        Country existingCountry = repository.findById(name).orElse(null);

        // Check if the country exists
        if(existingCountry != null){
            // Check updated attributes and replace old values with them
            if (updatedCountry.getName() != null) {
                existingCountry.setName(updatedCountry.getName());
            }
            if (updatedCountry.isActive()) {
                existingCountry.setActive(true);
            }

            Country savedCountry = repository.save(existingCountry);

            return modelMapper.map(savedCountry, CountryDTO.class);
        }

        return null;
    }

    @Transactional
    public void deleteCountry(String name) {
        repository.deleteById(name);
    }
}
