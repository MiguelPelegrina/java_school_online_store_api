package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookParametersFormatDTO;
import com.javaSchool.FinalTask3.entities.BookParametersFormat;
import com.javaSchool.FinalTask3.repositories.BookParameterFormatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookParameterFormatService {
    private final BookParameterFormatRepository repository;

    private final ModelMapper modelMapper;

    public List<BookParametersFormatDTO> getAllBookParametersFormat(){
        return repository.findAll()
                .stream()
                .map(bookParameterFormat ->
                        modelMapper.map(bookParameterFormat, BookParametersFormatDTO.class))
                .collect(Collectors.toList());
    }

    public BookParametersFormatDTO getBookParametersFormatById(String name){
        return modelMapper.map(repository.findById(name)
                .orElse(null), BookParametersFormatDTO.class);
    }

    @Transactional
    public BookParametersFormatDTO saveBookParametersFormat(BookParametersFormat bookParametersFormat){
        return modelMapper.map(repository.save(bookParametersFormat), BookParametersFormatDTO.class);
    }

    @Transactional
    public void deleteBookParametersFormat(String name){
        repository.deleteById(name);
    }
}
