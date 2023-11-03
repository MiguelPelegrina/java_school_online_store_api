package com.javaSchool.FinalTask3.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of the ModelMapper library. Instances a ModelMapper.
 */
@Configuration
public class ModelMapperConfig {
    /**
     * ModelMapper responsible for converting the instance of an entity into the related Data Transfer Object (DTO).
     * @return - Instance of ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
