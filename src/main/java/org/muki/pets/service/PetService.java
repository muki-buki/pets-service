package org.muki.pets.service;

import org.muki.pets.model.Pet;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface PetService {
    Pet getPet(String id);

    Pet createPet(Pet pet);

    Collection<Pet> getAllPets();
}
