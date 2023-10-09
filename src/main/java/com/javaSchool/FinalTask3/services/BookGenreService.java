package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.repositories.BookGenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookGenreRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.BookGenreController}. Obtains data from the {@link BookGenreRepository}
 * and returns the object(s) of the entity {@link BookGenre} as {@link BookGenreDTO} to the
 * {@link com.javaSchool.FinalTask3.controller.BookGenreController}.
 */
@Service
@Transactional(readOnly = true)
public class BookGenreService extends BaseService<BookGenre, BookGenreDTO, String>{
    // TODO I can't figure out which annotations to use to make lombok work with the parent constructor. Tried with
    //  @RequiredArgsConstructor in this class and @NoArgsConstructor(force = true) in parent class but then the
    //  repository is null. I assumed that lombok automatically calls the corresponding super constructor of the parent
    //  class, but I doesn't seem to be the case.
    // TODO Or public BookGenreService(JpaRepository<BookGenre, String> repository, ModelMapper modelMapper) { and that
    //  way I skip creating all the repositories?
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookGenre} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenre} to {@link BookGenreDTO}
     */
    public BookGenreService(BookGenreRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookGenre} entity.
     * @return Returns the {@link BookGenreDTO} class.
     */
    @Override
    protected Class<BookGenreDTO> getDTOClass() {
        return BookGenreDTO.class;
    }
}
