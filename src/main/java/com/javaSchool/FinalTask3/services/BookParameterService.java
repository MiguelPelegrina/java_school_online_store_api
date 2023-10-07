package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookParameterDTO;
import com.javaSchool.FinalTask3.entities.BookParameter;
import com.javaSchool.FinalTask3.repositories.BookParameterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookParameterService {
    private final BookParameterRepository repository;

    private final ModelMapper modelMapper;

    public List<BookParameterDTO> getAllBookParameters(){
        return repository.findAll()
                .stream()
                .map(bookParameter ->
                        modelMapper.map(bookParameter, BookParameterDTO.class))
                .collect(Collectors.toList());
    }

    public BookParameterDTO getBookParametersById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), BookParameterDTO.class);
    }

    @Transactional
    public BookParameterDTO saveBookParameter(BookParameter bookParameter){
        return modelMapper.map(repository.save(bookParameter), BookParameterDTO.class);
    }

    @Transactional
    public BookParameterDTO updateBookParameter(int id, BookParameter bookParameter){
        return repository.findById(id)
                .map(existingBookParameter -> {
                    existingBookParameter.setAuthor(bookParameter.getAuthor());
                    existingBookParameter.setFormat(bookParameter.getFormat());
                    existingBookParameter.setActive(bookParameter.isActive());
                    return modelMapper.map(existingBookParameter, BookParameterDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteBookParameter(int id){
        repository.deleteById(id);
    }
}
