package org.muki.pets.service.impl;

import org.muki.pets.model.Pet;
import org.muki.pets.service.PetService;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PetServiceImpl implements PetService {
    private static final Map<String, Pet> cache = new HashMap<>();
    private static final SecureRandom random = new SecureRandom();

    @Override
    public Pet getPet(String id) {
        return cache.get(id);
    }

    @Override
    public Pet createPet(Pet pet) {
        final String id = generateId();
        pet.setId(id);
        cache.put(id, pet);

        return pet;
    }

    @Override
    public Collection<Pet> getAllPets() {
        return cache.values();
    }

    private String generateId() {
        return new BigInteger(130, random).toString(32);
    }

}
