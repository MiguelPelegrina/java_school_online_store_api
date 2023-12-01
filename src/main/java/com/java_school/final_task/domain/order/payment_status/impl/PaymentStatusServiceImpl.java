package com.java_school.final_task.domain.order.payment_status.impl;

import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.genre.BookGenreRepository;
import com.java_school.final_task.domain.order.payment_status.*;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link PaymentStatusRepository} and the
 * {@link PaymentStatusRestControllerImpl}. Obtains data from the {@link PaymentStatusRepository}
 * and returns the object(s) of the entity {@link PaymentStatusEntity} as {@link PaymentStatusDTO} to the
 * {@link PaymentStatusRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class PaymentStatusServiceImpl
        extends AbstractServiceImpl<PaymentStatusRepository, PaymentStatusEntity, PaymentStatusDTO, String>
        implements PaymentStatusService {
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

    @Override
    public List<PaymentStatusDTO> getAllInstances(Optional<Boolean> active) {
        // Variables
        final QPaymentStatusEntity qPaymentStatus = QPaymentStatusEntity.paymentStatusEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qPaymentStatus.isActive.eq(aBoolean)));

        // Retrieve a list of payment statuses from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(
                        paymentStatus -> modelMapper.map(paymentStatus, getDTOClass()))
                .collect(Collectors.toList());
    }
}
