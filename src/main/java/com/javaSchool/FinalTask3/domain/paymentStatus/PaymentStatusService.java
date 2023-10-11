package com.javaSchool.FinalTask3.domain.paymentStatus;

import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreRepository;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link PaymentStatusRepository} and the
 * {@link PaymentStatusRestController}. Obtains data from the {@link PaymentStatusRepository}
 * and returns the object(s) of the entity {@link PaymentStatusEntity} as {@link PaymentStatusDTO} to the
 * {@link PaymentStatusRestController}.
 */
@Service
@Transactional(readOnly = true)
public class PaymentStatusService extends AbstractServiceWithUpdate<PaymentStatusEntity, PaymentStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookGenreEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookGenreEntity} to {@link BookGenreDTO}
     */
    public PaymentStatusService(PaymentStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link PaymentStatusEntity} entity.
     * @return Returns the {@link PaymentStatusDTO} class.
     */
    @Override
    protected Class<PaymentStatusDTO> getDTOClass() {
        return PaymentStatusDTO.class;
    }

    @Override
    protected String getEntityId(PaymentStatusEntity instance) {
        return instance.getName();
    }

    /**
     * Updates the values of an existing {@link PaymentStatusEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(PaymentStatusEntity existingInstance, PaymentStatusEntity newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}
