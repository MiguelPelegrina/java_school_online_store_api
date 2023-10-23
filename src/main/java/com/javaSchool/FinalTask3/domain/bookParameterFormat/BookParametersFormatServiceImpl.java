package com.javaSchool.FinalTask3.domain.bookParameterFormat;

import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookParametersFormatRepository} and the
 * {@link BookParametersFormatRestControllerImpl}. Obtains data from the
 * {@link BookParametersFormatRepository} and returns the object(s) of the entity {@link BookParametersFormatEntity} as
 * {@link BookParametersFormatDTO} to the {@link BookParametersFormatRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class BookParametersFormatServiceImpl extends AbstractServiceImpl<BookParametersFormatEntity, BookParametersFormatDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookParametersFormatEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParametersFormatEntity} to {@link BookParametersFormatDTO}
     */
    public BookParametersFormatServiceImpl(BookParametersFormatRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookParametersFormatEntity} entity.
     * @return Returns the {@link BookParametersFormatDTO} class.
     */
    @Override
    protected Class<BookParametersFormatDTO> getDTOClass() {
        return BookParametersFormatDTO.class;
    }

    @Override
    protected String getEntityId(BookParametersFormatEntity instance) {
        return instance.getName();
    }
}
