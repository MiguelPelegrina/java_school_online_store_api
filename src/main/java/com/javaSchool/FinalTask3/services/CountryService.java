package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(String name) {
        return countryRepository.findById(name).orElse(null);
    }

    @Transactional
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public Country updateCountry(String name, Country country) {
        Country savedCountry = countryRepository.findById(name).orElse(null);

        // Check if the country exists
        if (savedCountry != null){
            savedCountry.setName(country.getName());
            savedCountry.setActive(country.isActive());

            return countryRepository.save(savedCountry);
        }

        return null;
    }

    @Transactional
    public Country partiallyUpdate(String name, Country country){
        Country existingCountry = countryRepository.findById(name).orElse(null);

        // Check if the country exists
        if(existingCountry != null){
            // Check updated attributes and replace old values with them
            if (country.getName() != null) {
                existingCountry.setName(country.getName());
            }
            if (country.isActive()) {
                existingCountry.setActive(true);
            }

            return countryRepository.save(existingCountry);
        }

        return null;
    }

    @Transactional
    public void deleteCountry(String name) {
        countryRepository.deleteById(name);
    }
}
