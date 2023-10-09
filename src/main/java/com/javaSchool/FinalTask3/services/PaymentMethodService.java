package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.dtos.PaymentMethodDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.entities.Country;
import com.javaSchool.FinalTask3.entities.PaymentMethod;
import com.javaSchool.FinalTask3.repositories.PaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link PaymentMethodRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.PaymentMethodController}. Obtains data from the {@link PaymentMethodRepository}
 * and returns the object(s) of the entity {@link PaymentMethod} as {@link PaymentMethod} to the
 * {@link com.javaSchool.FinalTask3.controller.PaymentMethodController}.
 */
@Service
@Transactional(readOnly = true)
public class PaymentMethodService extends BaseServiceWithUpdate<PaymentMethod, PaymentMethodDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link PaymentMethodRepository} of the {@link BookGenre} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenre} to {@link BookGenreDTO}
     */
    public PaymentMethodService(PaymentMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }


    /**
     * Returns the DTO class of the {@link PaymentMethod} entity.
     * @return Returns the {@link PaymentMethodDTO} class.
     */
    @Override
    protected Class<PaymentMethodDTO> getDTOClass() {
        return PaymentMethodDTO.class;
    }

    /**
     * Updates the values of an existing {@link PaymentMethod} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(PaymentMethod existingInstance, PaymentMethod newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}
