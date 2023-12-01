package com.java_school.final_task.domain.order.payment_method.impl;

import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.order.payment_method.*;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link PaymentMethodRepository} and the
 * {@link PaymentMethodRestControllerImpl}. Obtains data from the {@link PaymentMethodRepository}
 * and returns the object(s) of the entity {@link PaymentMethodEntity} as {@link PaymentMethodEntity} to the
 * {@link PaymentMethodRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class PaymentMethodServiceImpl
        extends AbstractServiceImpl<PaymentMethodRepository, PaymentMethodEntity, PaymentMethodDTO, String>
        implements PaymentMethodService {
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

    @Override
    public List<PaymentMethodDTO> getAllInstances(Optional<Boolean> active) {
        // Variables
        final QPaymentMethodEntity qPaymentMethod = QPaymentMethodEntity.paymentMethodEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qPaymentMethod.isActive.eq(aBoolean)));

        // Retrieve a list of payment methods from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(
                        paymentMethod -> modelMapper.map(paymentMethod, getDTOClass()))
                .collect(Collectors.toList());
    }
}
