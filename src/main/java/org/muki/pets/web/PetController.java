package org.muki.pets.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PetController {
    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping(value = "/1/pets", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String[] getPets() {
        return new String[]{};
    }

    @RequestMapping(value = "/1/pets", method = POST)
    @ResponseBody
    @ResponseStatus(CREATED)
    public CreatePetWebResponse createPetV1(@RequestBody CreatePetWebRequest webRequest) throws Exception {
        log.info("Processing create pet web request");

        CreatePetWebResponse response = new CreatePetWebResponse();
        response.setId("temp_id");

        return response;
    }

}
