package com.javaSchool.FinalTask3.domain.order.paymentStatus.impl;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreRepository;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusDTO;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusEntity;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link PaymentStatusRepository} and the
 * {@link PaymentStatusRestControllerImpl}. Obtains data from the {@link PaymentStatusRepository}
 * and returns the object(s) of the entity {@link PaymentStatusEntity} as {@link PaymentStatusDTO} to the
 * {@link PaymentStatusRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class PaymentStatusServiceImpl
        extends AbstractServiceImpl<PaymentStatusRepository, PaymentStatusEntity, PaymentStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookGenreEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenreEntity} to {@link BookGenreDTO}
     */
    public PaymentStatusServiceImpl(PaymentStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<PaymentStatusDTO> getDTOClass() {
        return PaymentStatusDTO.class;
    }

    @Override
    public String getEntityId(PaymentStatusEntity instance) {
        return instance.getName();
    }
}
