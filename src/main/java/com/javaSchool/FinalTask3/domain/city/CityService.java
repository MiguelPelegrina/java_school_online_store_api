package com.javaSchool.FinalTask3.domain.city;

import com.javaSchool.FinalTask3.domain.country.CountryRepository;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link CityRepository} and the
 * {@link CityRestController}. Obtains data from the
 * {@link CityRepository} and returns the object(s) of the entity {@link CityEntity} as
 * {@link CityDTO} to the {@link CityRestController}.
 */
@Service
@Transactional(readOnly = true)
public class CityService extends AbstractService<CityEntity, CityDTO, String> {
    // TODO Not sure if necessary
    private final CountryRepository countryRepository;

    /**
     * All arguments constructor.
     *
     * @param repository        {@link CityRepository} of the {@link CityEntity} instance.
     * @param modelMapper       ModelMapper that converts the {@link CityRepository} to {@link CityDTO}
     * @param countryRepository {@link CountryRepository} to access the instance of the
     * {@link com.javaSchool.FinalTask3.domain.country.CountryEntity} of this {@link CityEntity} instance.
     */
    public CityService(CityRepository repository, ModelMapper modelMapper, CountryRepository countryRepository) {
        super(repository, modelMapper);
        // TODO Not sure if necessary
        this.countryRepository = countryRepository;
    }

    // TODO Not sure if necessary
    @Override
    @Transactional
    public CityDTO saveInstance(CityEntity instance){
        instance.setCountryName(countryRepository.getReferenceById(instance.getCountryName().getName()));
        return modelMapper.map(repository.save(instance), getDTOClass());
    }

    /**
     * Returns the DTO class of the {@link CityEntity} entity.
     * @return Returns the {@link CityDTO} class.
     */
    @Override
    protected Class<CityDTO> getDTOClass() {
        return CityDTO.class;
    }

    @Override
    protected String getEntityId(CityEntity instance) {
        return instance.getName();
    }
}
