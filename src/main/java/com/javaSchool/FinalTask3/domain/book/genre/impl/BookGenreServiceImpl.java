package com.javaSchool.FinalTask3.domain.book.genre.impl;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link BookGenreRepository} and the {@link BookGenreRestControllerImpl}.
 * Obtains data from the {@link BookGenreRepository} and returns the object(s) of the entity {@link BookGenreEntity}
 * as {@link BookGenreDTO} to the {@link BookGenreRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class BookGenreServiceImpl extends AbstractServiceImpl<BookGenreRepository, BookGenreEntity, BookGenreDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookGenreEntity}.
     * @param modelMapper ModelMapper that converts the {@link BookGenreEntity} instance to {@link BookGenreDTO}
     */
    public BookGenreServiceImpl(BookGenreRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<BookGenreDTO> getDTOClass() {
        return BookGenreDTO.class;
    }

    @Override
    public String getEntityId(BookGenreEntity instance) {
        return instance.getName();
    }
}
