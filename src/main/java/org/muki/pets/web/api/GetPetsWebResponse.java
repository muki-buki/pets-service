package org.muki.pets.web.api;

import java.io.Serializable;
import java.util.Collection;

public class GetPetsWebResponse {

    private Collection<GetPetWebResponse> pets;

    public void setPets(Collection<GetPetWebResponse> pets) {
        this.pets = pets;
    }

    public Collection<GetPetWebResponse> getPets() {
        return pets;
    }
}
