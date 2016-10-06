package org.muki.pets.web.api;

public interface EndpointPaths {
    // api version
    String VERSION = "/1";

    String PETS_URL = VERSION + "/pets";
    String PET_URL = VERSION + "/pets/{id}";
}