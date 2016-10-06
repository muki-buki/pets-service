package org.muki.pets.web;

import org.modelmapper.ModelMapper;
import org.muki.pets.model.Pet;
import org.muki.pets.service.PetService;
import org.muki.pets.web.api.CreatePetWebRequest;
import org.muki.pets.web.api.CreatePetWebResponse;
import org.muki.pets.web.api.GetPetWebResponse;
import org.muki.pets.web.api.GetPetsWebResponse;
import org.muki.pets.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.stream.Collectors;

import static org.muki.pets.web.api.EndpointPaths.PETS_URL;
import static org.muki.pets.web.api.EndpointPaths.PET_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class PetController {
    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper mapper;


    @RequestMapping(path = PETS_URL, method = GET)
    @ResponseBody
    @ResponseStatus(OK)
    public GetPetsWebResponse getPets() {
        final GetPetsWebResponse response = new GetPetsWebResponse();
        // TODO add pagination
        response.setPets(petService
                .getAllPets()
                .stream()
                .map(pet -> mapper.map(pet, GetPetWebResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

    @RequestMapping(value = PETS_URL, method = POST)
    @ResponseBody
    @ResponseStatus(CREATED)
    public CreatePetWebResponse createPet(@RequestBody CreatePetWebRequest webRequest) throws Exception {
        log.info("Processing create pet web request");

        Pet pet = mapper.map(webRequest, Pet.class);
        pet = petService.createPet(pet);

        CreatePetWebResponse response = new CreatePetWebResponse();
        response.setId(pet.getId());

        return response;
    }

    @RequestMapping(path = PET_URL, method = GET)
    @ResponseBody
    @ResponseStatus(OK)
    public GetPetWebResponse getPet(@PathVariable("id") String id, HttpServletRequest request) throws Throwable {
        log.info("Processing get pet request | id: " + id);

        Pet pet = petService.getPet(id);
        if (pet == null) {
            throw new ResourceNotFoundException("No pet found with id: " + id);
        }

        return mapper.map(pet, GetPetWebResponse.class);
    }
}
