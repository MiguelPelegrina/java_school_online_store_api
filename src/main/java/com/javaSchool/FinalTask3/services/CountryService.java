package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public Country getCountryById(String name) {
        return countryRepository.findById(name).orElse(null);
    }

    @Transactional
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String name) {
        countryRepository.deleteById(name);
    }
}
