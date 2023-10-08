package com.javaSchool.FinalTask3.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO This way or inserting the @Bean in Main?

/**
 * Configuration of the ModelMapper library. Instances a ModelMapper
 */
@Configuration
public class ModelMapperConfig {
    /**
     * A modelMapper is responsible for converting the instance of an entity into the related Data Transfer Object (DTO)
     * @return Returns an instance of ModelMapper
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
