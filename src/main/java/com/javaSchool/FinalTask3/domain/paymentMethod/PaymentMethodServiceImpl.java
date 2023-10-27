package com.javaSchool.FinalTask3.domain.paymentMethod;

import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link PaymentMethodRepository} and the
 * {@link PaymentMethodRestControllerImpl}. Obtains data from the {@link PaymentMethodRepository}
 * and returns the object(s) of the entity {@link PaymentMethodEntity} as {@link PaymentMethodEntity} to the
 * {@link PaymentMethodRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class PaymentMethodServiceImpl extends AbstractServiceImpl<PaymentMethodEntity, PaymentMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link PaymentMethodRepository} of the {@link BookGenreEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenreEntity} to {@link BookGenreDTO}
     */
    public PaymentMethodServiceImpl(PaymentMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }


    /**
     * Returns the DTO class of the {@link PaymentMethodEntity} entity.
     * @return Returns the {@link PaymentMethodDTO} class.
     */
    @Override
    protected Class<PaymentMethodDTO> getDTOClass() {
        return PaymentMethodDTO.class;
    }

    @Override
    protected String getEntityId(PaymentMethodEntity instance) {
        return instance.getName();
    }
}