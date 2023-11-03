package com.javaSchool.FinalTask3.domain.order.paymentMethod.impl;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodDTO;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodEntity;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodRepository;
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
public class PaymentMethodServiceImpl
        extends AbstractServiceImpl<PaymentMethodRepository, PaymentMethodEntity, PaymentMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link PaymentMethodRepository} of the {@link BookGenreEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenreEntity} to {@link BookGenreDTO}
     */
    public PaymentMethodServiceImpl(PaymentMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<PaymentMethodDTO> getDTOClass() {
        return PaymentMethodDTO.class;
    }

    @Override
    public String getEntityId(PaymentMethodEntity instance) {
        return instance.getName();
    }
}
