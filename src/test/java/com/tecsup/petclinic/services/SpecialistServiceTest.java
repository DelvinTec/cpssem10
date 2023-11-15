package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Specialist;
import com.tecsup.petclinic.exception.SpecialistNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j

public class SpecialistServiceTest {

    @Autowired
    private SpecialistService specialistService;

    @Test
    public void testCreateSpecialist() {

        String SPEC_NAME = "Alessandro";
        String SPEC_OFFICE = "Quiroz";
        Integer SPEC_H_OPEN = 7;
        Integer SPEC_H_CLOSE = 14;

        Specialist specialist = new Specialist(SPEC_NAME, SPEC_OFFICE, SPEC_H_OPEN, SPEC_H_CLOSE);

        Specialist specialistCreated = this.specialistService.create(specialist);

        log.info("SPECIALIST CREATED :" + specialistCreated.toString());

        assertNotNull(specialistCreated.getId());
        assertEquals(SPEC_NAME, specialistCreated.getName());
        assertEquals(SPEC_OFFICE, specialistCreated.getOffice());
        assertEquals(SPEC_H_OPEN, specialistCreated.getH_open());
        assertEquals(SPEC_H_CLOSE, specialistCreated.getH_close());

    }
}
