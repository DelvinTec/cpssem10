package com.tecsup.petclinic.repositories;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository
    extends CrudRepository<Owner, Integer>{


    // Fetch pets by last name
    List<Owner> findByLast(String last);

    @Override
    List<Owner> findAll();
}
