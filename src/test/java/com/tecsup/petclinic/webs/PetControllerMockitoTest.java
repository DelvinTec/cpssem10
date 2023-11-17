package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.mapper.PetMapper;
import com.tecsup.petclinic.repositories.PetRepository;
import com.tecsup.petclinic.services.PetService;
import com.tecsup.petclinic.util.TObjectCreator;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class PetControllerMockitoTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private PetService petService;

    PetMapper mapper = Mappers.getMapper(PetMapper.class);

    @BeforeEach
    void setUp() {
        // Initialize RestAssured
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void testFindAllPets() throws Exception {

        int NRO_RECORD = 5;
        int ID_FIRST_RECORD = 1;

        List<PetTO> petTOs  = TObjectCreator.getAllPetTOs();

        List<Pet> pets  = this.mapper.toPetList(petTOs);

        Mockito.when(petService.findAll())
                .thenReturn(pets);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/pets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(NRO_RECORD)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(ID_FIRST_RECORD)));

    }


    /**
     *
     * @throws Exception
     *
     */
    @Test
    public void testFindPetOK() throws Exception {

        PetTO petTO  = TObjectCreator.getPetTO();

        Pet pet  = this.mapper.toPet(petTO);

        Mockito.when(petService.findById(pet.getId()))
                .thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.get("/pets/1"))  // Object must be BASIL
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(petTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(petTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeId", CoreMatchers.is(petTO.getTypeId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerId", CoreMatchers.is(petTO.getOwnerId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", CoreMatchers.is(petTO.getBirthDate())));
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testFindPetKO() throws Exception {

        Integer ID_NOT_EXIST = 666;

        Mockito.when(this.petService.findById(ID_NOT_EXIST))
                .thenThrow(new PetNotFoundException("Record not found...!"));

        mockMvc.perform(MockMvcRequestBuilders.get("/pets/666"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * @throws Exception
     */
    @Test
    public void testCreatePet() throws Exception {

        PetTO newPetTO  = TObjectCreator.newPetTO();

        Pet newPet  = this.mapper.toPet(newPetTO);

        Mockito.when(petService.create(newPet))
                .thenReturn(newPet);

        mockMvc.perform(MockMvcRequestBuilders.post("/pets")
                        .content(om.writeValueAsString(newPetTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(newPetTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeId", CoreMatchers.is(newPetTO.getTypeId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerId", CoreMatchers.is(newPetTO.getOwnerId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", CoreMatchers.is(newPetTO.getBirthDate())));

    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testDeletePet() throws Exception {

        // ------------ Create ---------------

        PetTO newPetTO  = TObjectCreator.newPetTOForDelete();

        Pet newPet  = this.mapper.toPet(newPetTO);

        Mockito.when(petService.create(newPet))
                .thenReturn(newPet);

        ResultActions mvcActions = mockMvc.perform(MockMvcRequestBuilders.post("/pets")
                        .content(om.writeValueAsString(newPetTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();


        // ------------ Delete ---------------

        Mockito.doNothing().when(this.petService).delete(newPet.getId());

        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(MockMvcRequestBuilders.delete("/pets/" + id ))
                /*.andDo(print())*/
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
