package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.dtos.PaymentStatusDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.entities.Country;
import com.javaSchool.FinalTask3.entities.PaymentStatus;
import com.javaSchool.FinalTask3.repositories.BookGenreRepository;
import com.javaSchool.FinalTask3.repositories.PaymentStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link PaymentStatusRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.PaymentStatusController}. Obtains data from the {@link PaymentStatusRepository}
 * and returns the object(s) of the entity {@link PaymentStatus} as {@link PaymentStatusDTO} to the
 * {@link com.javaSchool.FinalTask3.controller.PaymentStatusController}.
 */
@Service
@Transactional(readOnly = true)
public class PaymentStatusService extends BaseServiceWithUpdate<PaymentStatus, PaymentStatusDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookGenre} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenre} to {@link BookGenreDTO}
     */
    public PaymentStatusService(PaymentStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link PaymentStatus} entity.
     * @return Returns the {@link PaymentStatusDTO} class.
     */
    @Override
    protected Class<PaymentStatusDTO> getDTOClass() {
        return PaymentStatusDTO.class;
    }

    /**
     * Updates the values of an existing {@link PaymentStatus} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(PaymentStatus existingInstance, PaymentStatus newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}
