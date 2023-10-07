package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.repositories.BookGenreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookGenreService {
    private final BookGenreRepository repository;

    private final ModelMapper modelMapper;

    public List<BookGenreDTO> getAllBookGenres(){
        return repository.findAll()
                .stream()
                .map(bookGenre ->
                        modelMapper.map(bookGenre, BookGenreDTO.class))
                .collect(Collectors.toList());
    }

    public BookGenreDTO getBookGenreById(String name){
        return modelMapper.map(repository.findById(name)
                .orElse(null), BookGenreDTO.class);
    }

    @Transactional
    public BookGenreDTO saveBookGenre(BookGenre bookGenre){
        return modelMapper.map(repository.save(bookGenre), BookGenreDTO.class);
    }

    @Transactional
    public void deleteBookGenre(String name){
        repository.deleteById(name);
    }
}
