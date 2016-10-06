package org.muki.pets.service;


import org.modelmapper.ModelMapper;
import org.muki.pets.service.impl.PetServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Bean
    public PetService petService() {
        return new PetServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
