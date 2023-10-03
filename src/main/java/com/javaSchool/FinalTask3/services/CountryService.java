package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository repository;

    @Transactional
    public List<Country> getAllEntities() {
        return repository.findAll();
    }

    @Transactional
    public Country getEntityById(String name) {
        return repository.findById(name).orElse(null);
    }

    @Transactional
    public Country saveEntity(Country entity) {
        return repository.save(entity);
    }

    @Transactional
    public void deleteEntity(String name) {
        repository.deleteById(name);
    }
}
