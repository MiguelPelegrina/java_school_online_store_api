package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookDTO;
import com.javaSchool.FinalTask3.entities.Book;
import com.javaSchool.FinalTask3.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository repository;

    private final ModelMapper modelMapper;

    public List<BookDTO> getAllBooks(){
        return repository.findAll()
                .stream()
                .map(book ->
                        modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), BookDTO.class);
    }

    @Transactional
    public BookDTO saveBook(Book book){
        return modelMapper.map(repository.save(book), BookDTO.class);
    }

    @Transactional
    public BookDTO updateBook(int id, Book book){
        return repository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setPrice(book.getPrice());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setGenre(book.getGenre());
                    existingBook.setParameters(book.getParameters());
                    existingBook.setStock(book.getStock());
                    existingBook.setActive(book.isActive());
                    existingBook.setImage(book.getImage());
                    return modelMapper.map(repository.save(existingBook), BookDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteBook(int id){
        repository.deleteById(id);
    }
}
