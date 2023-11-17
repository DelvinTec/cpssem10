package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.PetTO;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
public class PetControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllPets() throws Exception {

        //int NRO_RECORD = 73;
        int ID_FIRST_RECORD = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/pets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //		    .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(ID_FIRST_RECORD)));
    }


    /**
     *
     * @throws Exception
     *
     */
    @Test
    public void testFindPetOK() throws Exception {

        String NAME_PET = "Leo";
        int TYPE_ID = 1;
        int OWNER_ID = 1;
        String BIRTH_DATE = "2000-09-07";

        mockMvc.perform(MockMvcRequestBuilders.get("/pets/1"))  // Object must be BASIL
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(NAME_PET)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeId", CoreMatchers.is(TYPE_ID)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerId", CoreMatchers.is(OWNER_ID)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", CoreMatchers.is(BIRTH_DATE)));
    }
    /**
     *
     * @throws Exception
     */
    @Test
    public void testFindPetKO() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/pets/666"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    /**
     * @throws Exception
     */
    @Test
    public void testCreatePet() throws Exception {

        String NAME_PET = "Beethoven";
        int TYPE_ID = 1;
        int OWNER_ID = 1;
        String BIRTH_DATE = "2020-05-20";

        PetTO newPetTO = new PetTO();
        newPetTO.setName(NAME_PET);
        newPetTO.setTypeId(TYPE_ID);
        newPetTO.setOwnerId(OWNER_ID);
        newPetTO.setBirthDate(BIRTH_DATE);

        mockMvc.perform(MockMvcRequestBuilders.post("/pets")
                        .content(om.writeValueAsString(newPetTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(NAME_PET)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeId", CoreMatchers.is(TYPE_ID)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerId", CoreMatchers.is(OWNER_ID)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", CoreMatchers.is(BIRTH_DATE)));

    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testDeletePet() throws Exception {

        String NAME_PET = "Beethoven3";
        int TYPE_ID = 1;
        int OWNER_ID = 1;
        String BIRTH_DATE = "2020-05-20";

        PetTO newPetTO = new PetTO();
        newPetTO.setName(NAME_PET);
        newPetTO.setTypeId(TYPE_ID);
        newPetTO.setOwnerId(OWNER_ID);
        newPetTO.setBirthDate(BIRTH_DATE);

        ResultActions mvcActions = mockMvc.perform(MockMvcRequestBuilders.post("/pets")
                        .content(om.writeValueAsString(newPetTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();

        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(MockMvcRequestBuilders.delete("/pets/" + id ))
                /*.andDo(print())*/
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
