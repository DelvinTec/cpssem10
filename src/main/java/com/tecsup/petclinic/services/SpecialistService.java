package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Specialist;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.SpecialistNotFoundException;
public interface SpecialistService {

    /**
     *
     * @param specialist
     * @return
     */
    Specialist create(Specialist specialist);

    /**
     *
     * @param specialist
     * @return
     */
    Specialist update(Specialist specialist);

    /**
     *
     * @param id
     * @throws SpecialistNotFoundException
     */
    void delete(Integer id) throws SpecialistNotFoundException;

    /**
     *
     * @param id
     * @return
     */
    Specialist findById(Integer id) throws SpecialistNotFoundException;

    /**
     *
     * @param name
     * @return
     */
    List<Specialist> findByName(String name);

    /**
     *
     * @return
     */
    List<Specialist> findAll();
}
