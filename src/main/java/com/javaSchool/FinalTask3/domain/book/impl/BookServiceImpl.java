package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.*;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
@Service
@Transactional(readOnly = true)
public class BookServiceImpl
        extends AbstractServiceImpl<BookRepository, BookEntity, BookDTO, Integer>
        implements BookService {
    /**
     * All arguments constructor.
     * @param repository {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     */
    public BookServiceImpl(BookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<BookDTO> getDTOClass() {
        return BookDTO.class;
    }

    @Override
    public Integer getEntityId(BookEntity instance) {
        return instance.getId();
    }

    // TODO
    //  Scalable by
    //  - Differentiating between filtering with AND (requires to be advanced filter in FE) and OR (searching by title,
    //  or author, or ISBN) --> just use another RequestParam Optional<Boolean> advanced or a different mapping?
    //  - Adding an Array of String for sorting
    //  - Adding an Array of Genres for filtering
    @Override
    public Page<BookDTO> getAllInstances(String name, Optional<Boolean> active, String genre, PageRequest pageRequest) {
        // Variables
        final QBookEntity qBook = QBookEntity.bookEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qBook.isActive.eq(aBoolean)));

        if(!name.isEmpty()){
            queryBuilder.and(qBook.title.containsIgnoreCase(name).or(qBook.parameters.author.containsIgnoreCase(name)));
        }

        if (!genre.isEmpty()) {
            queryBuilder.and(qBook.genre.name.containsIgnoreCase(genre));
        }

        // Find the data in the repository
        Page<BookEntity> pageEntities = this.repository.findAll(queryBuilder, pageRequest);

        // Convert the book page to a bookDTO page
        return pageEntities.map(book ->
            modelMapper.map(book, this.getDTOClass())
        );
    }
}
