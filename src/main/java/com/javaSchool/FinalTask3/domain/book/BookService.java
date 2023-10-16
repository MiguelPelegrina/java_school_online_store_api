package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestController}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestController}.
 */
@Service
@Transactional(readOnly = true)
public class BookService extends AbstractService<BookEntity, BookDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     */
    public BookService(BookRepository repository, ModelMapper modelMapper) {
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
}
