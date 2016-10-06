package org.muki.pets.web;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.muki.pets.service.PetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class PetControllerTestConfig {

    @Bean
    public PetController petController() {
        return new PetController();
    }

    @Bean
    public PetService petService() {
        return mock(PetService.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
