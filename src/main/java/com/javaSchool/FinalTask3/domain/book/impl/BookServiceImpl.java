package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.BookDTO;
import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.domain.book.BookRepository;
import com.javaSchool.FinalTask3.domain.book.QBookEntity;
import com.javaSchool.FinalTask3.domain.book.impl.BookRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class BookServiceImpl extends AbstractServiceImpl<BookEntity, BookDTO, Integer>{
    /**
     * All arguments constructor.
     * @param repository {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     */
    public BookServiceImpl(BookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookEntity} entity.
     * @return Returns the {@link BookDTO} class.
     */
    @Override
    protected Class<BookDTO> getDTOClass() {
        return BookDTO.class;
    }

    @Override
    protected Integer getEntityId(BookEntity instance) {
        return instance.getId();
    }

    // TODO
    //  Add paging and sorting
    //  Abstractable?
    //  Differentiate between filtering with AND (requires advanced filter in FE) and OR (searching by title, or
    //  author, or ISBN) --> just use another RequestParam Optional<Boolean> advanced or a different mapping?
    public List<BookDTO> getAllInstances(
                @RequestParam(name = "name", defaultValue = "") String name,
                @RequestParam("active") Optional<Boolean> active,
                @RequestParam("sort") Optional<String> sort,
                @RequestParam("page") Optional<Integer> page,
                @RequestParam("size") Optional<Integer> size
    ) {
        // Variables
        final BookRepository bookRepository = (BookRepository) repository;
        final QBookEntity qBook = QBookEntity.bookEntity;
        final BooleanBuilder where = new BooleanBuilder();

        // Check which parameters are present
        active.ifPresent(aBoolean -> where.and(qBook.isActive.eq(aBoolean)));
        where.and(qBook.title.containsIgnoreCase(name));
        where.or(qBook.parameters.author.containsIgnoreCase(name));

        // Find the data in the repository
        List<BookEntity> bookList = bookRepository.findAll(where);

        // Convert 'bookList' to 'books' using DTO mapping
        // Add the converted books to the 'books' list
        return bookList.stream().map(book -> modelMapper.map(book, this.getDTOClass())).collect(Collectors.toList());
    }
}
