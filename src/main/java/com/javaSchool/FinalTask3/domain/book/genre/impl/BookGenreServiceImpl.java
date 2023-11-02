package com.javaSchool.FinalTask3.domain.book.genre.impl;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookGenreRepository} and the {@link BookGenreRestControllerImpl}.
 * Obtains data from the {@link BookGenreRepository} and returns the object(s) of the entity {@link BookGenreEntity}
 * as {@link BookGenreDTO} to the {@link BookGenreRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class BookGenreServiceImpl extends AbstractServiceImpl<BookGenreEntity, BookGenreDTO, String> {
    // TODO I can't figure out which annotations to use to make lombok work with the parent constructor. Tried with
    //  @RequiredArgsConstructor in this class and @NoArgsConstructor(force = true) in parent class but then the
    //  repository is null. I assumed that lombok automatically calls the corresponding super constructor of the parent
    //  class, but I doesn't seem to be the case.
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookGenreEntity}.
     * @param modelMapper ModelMapper that converts the {@link BookGenreEntity} instance to {@link BookGenreDTO}
     */
    public BookGenreServiceImpl(BookGenreRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookGenreEntity} entity.
     * @return Returns the {@link BookGenreDTO} class.
     */
    @Override
    protected Class<BookGenreDTO> getDTOClass() {
        return BookGenreDTO.class;
    }

    @Override
    protected String getEntityId(BookGenreEntity instance) {
        return instance.getName();
    }
}