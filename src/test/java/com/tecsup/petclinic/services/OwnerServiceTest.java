package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
   	private OwnerService ownerService;

	@Test
	public void testFindOwnerById() {

		Integer ID = 1;
		String NAME = "Franklin";
		Owner owner = null;
		
		try {
			
			owner = this.ownerService.findById(ID);
			
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + owner);

		assertEquals(NAME, owner.getLast());

	}

	@Test
	public void testCreateOwner() {

		String OWNER_NAME = "Alessandro";
		String OWNER_LAST = "Quiroz";
		String OWNER_ADDRESS = "043 SJL";
		String OWNER_CITY = "Lima";
		Long OWNER_TELEPHONE = 9925551022L;

		Owner owner = new Owner(OWNER_NAME, OWNER_LAST, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);

		Owner ownerCreated = this.ownerService.create(owner);

		log.info("OWNER CREATED :" + ownerCreated.toString());

		assertNotNull(ownerCreated.getId());
		assertEquals(OWNER_NAME, ownerCreated.getFirst());
		assertEquals(OWNER_LAST, ownerCreated.getLast());
		assertEquals(OWNER_ADDRESS, ownerCreated.getAddress());
		assertEquals(OWNER_CITY, ownerCreated.getCity());
		assertEquals(OWNER_TELEPHONE, ownerCreated.getTelephone());

	}

	@Test
	public void testUpdateOwner() {

		String OWNER_NAME = "Piero";
		String OWNER_LAST = "Quiroz";
		String OWNER_ADDRESS = "043 SJL";
		String OWNER_CITY = "Lima";
		Long OWNER_TELEPHONE = 9925551022L;

		String UP_OWNER_NAME = "Alessandro";
		String UP_OWNER_LAST = "Quiroz";
		String UP_OWNER_ADDRESS = "042 SJL";
		String UP_OWNER_CITY = "Cuzco";
		Long UP_OWNER_TELEPHONE = 9926671022L;

		Owner owner = new Owner(OWNER_NAME, OWNER_LAST, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);

		// ------------ Create ---------------

		log.info(">" + owner);
		Owner ownerCreated = this.ownerService.create(owner);
		log.info(">>" + ownerCreated);

		// ------------ Update ---------------

		// Prepare data for update
		ownerCreated.setFirst(UP_OWNER_NAME);
		ownerCreated.setLast(UP_OWNER_LAST);
		ownerCreated.setAddress(UP_OWNER_ADDRESS);
		ownerCreated.setCity(UP_OWNER_CITY);
		ownerCreated.setTelephone(UP_OWNER_TELEPHONE);

		// Execute update
		Owner upgradeOwner = this.ownerService.update(ownerCreated);
		log.info(">>>>" + upgradeOwner);

		//            EXPECTED        ACTUAL
		assertEquals(UP_OWNER_NAME, upgradeOwner.getFirst());
		assertEquals(UP_OWNER_LAST, upgradeOwner.getLast());
		assertEquals(UP_OWNER_ADDRESS, upgradeOwner.getAddress());
		assertEquals(UP_OWNER_CITY, upgradeOwner.getCity());
		assertEquals(UP_OWNER_TELEPHONE, upgradeOwner.getTelephone());
	}

	@Test
	public void testDeleteOwner() {

		String OWNER_NAME = "Pancho";
		String OWNER_LAST = "Pancho";
		String OWNER_ADDRESS = "";
		String OWNER_CITY = "";
		Long OWNER_TELEPHONE = 6085551022L;

		// ------------ Create ---------------

		Owner owner = new Owner(OWNER_NAME, OWNER_LAST, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		owner = this.ownerService.create(owner);
		log.info("" + owner);

		// ------------ Delete ---------------

		try {
			this.ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		// ------------ Validation ---------------

		try {
			this.ownerService.findById(owner.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}

	}
}
