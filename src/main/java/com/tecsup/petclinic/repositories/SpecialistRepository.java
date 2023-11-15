package com.tecsup.petclinic.repositories;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Specialist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepository
        extends CrudRepository<Specialist, Integer>{

    // Fetch specialist by name
    List<Specialist> findByName(String name);

    @Override
    List<Specialist> findAll();
}
