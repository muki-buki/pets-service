package org.muki.pets.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.muki.pets.model.Pet;
import org.muki.pets.service.PetService;
import org.muki.pets.utils.Builder;
import org.muki.pets.web.config.ObjectMappingConfig;
import org.muki.pets.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.muki.pets.web.api.EndpointPaths.PETS_URL;
import static org.muki.pets.web.api.EndpointPaths.PET_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PetControllerTestConfig.class, ObjectMappingConfig.class, WebConfig.class})
@WebAppConfiguration
public class PetControllerTest {

    @Autowired
    private PetService petService;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Gson gson;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jacksonMapper;

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

        Pet p1 = new Builder<Pet>() {
        }
                .set("name", "p1")
                .set("category", "cat")
                .set("id", id)
                .build();

        when(petService.getAllPets())
                .thenReturn(Arrays.asList(p1));

        MvcResult res = mockMvc.perform(get(PETS_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pets[0].id").value(id))
                .andReturn();
        // to verify the response
//        String content = res.getResponse().getContentAsString();
    }

    @Test
    public void createPet() throws Exception {
        final Pet petRequest = new Builder<Pet>() {
        }
                .set("name", "kitty")
                .set("category", "cat")
                .build();

        final Pet pet = new Builder<Pet>() {
        }
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
        final String id = "2016";
        final String name = "zorro";
        final String category = "dogs";

        final Pet pet = new Builder<Pet>() {
        }       .set("id", id)
                .set("name", name)
                .set("category", category)
                .build();

        when(petService.getPet(eq(id))).thenReturn(pet);

        mockMvc.perform(get(PET_URL, id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("category").value(category));;
    }

}