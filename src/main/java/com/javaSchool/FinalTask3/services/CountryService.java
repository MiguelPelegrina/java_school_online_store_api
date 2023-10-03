package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.entity.Country;
import com.javaSchool.FinalTask3.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/countries")
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository repository;

    public List<Country> getAllEntities() {
        return repository.findAll();
    }

    public Country getEntityById(String name) {
        return repository.findById(name).orElse(null);
    }

    public Country saveEntity(Country entity) {
        return repository.save(entity);
    }

    public void deleteEntity(String name) {
        repository.deleteById(name);
    }
}
