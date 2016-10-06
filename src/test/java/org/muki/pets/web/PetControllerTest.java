package org.muki.pets.web;

import com.google.gson.Gson;
import com.sun.tools.javac.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.muki.pets.model.Pet;
import org.muki.pets.service.PetService;
import org.muki.pets.utils.Builder;
import org.muki.pets.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.ArgumentMatcher;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.argThat;

import static org.muki.pets.web.api.EndpointPaths.PETS_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PetControllerTestConfig.class})
@WebAppConfiguration
public class PetControllerTest {

    @Autowired
    private PetService petService;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Gson gson;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        reset(petService);
    }

    @Test
    public void getPets() throws Exception {
        final String id = "id";

        Pet p1 = new Builder<Pet>() {}
                .set("name", "p1")
                .set("category", "cat")
                .set("id", id)
                .build();

        when(petService.getAllPets())
                .thenReturn(List.of(p1));

        mockMvc.perform(get(PETS_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id));
    }

    @Test
    public void createPet() throws Exception {
        final Pet petRequest = new Builder<Pet>() {}
                .set("name", "kitty")
                .set("category", "cat")
                .build();

        final Pet pet = new Builder<Pet>() {}
                .set("id", "666")
                .set("name", petRequest.getName())
                .set("category", petRequest.getCategory())
                .build();

        when(petService.createPet(argThat(new ArgumentMatcher<Pet>() {

            @Override
            public boolean matches(Object argument) {
                return true;
            }

        }))).thenReturn(pet);



        String body = gson.toJson(petRequest);

        mockMvc.perform(post(PETS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.getBytes())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(pet.getId()));
    }

    @Test
    public void getPet() throws Exception {

    }

}