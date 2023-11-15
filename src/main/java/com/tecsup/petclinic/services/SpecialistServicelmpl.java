package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import com.tecsup.petclinic.entities.Specialist;
import com.tecsup.petclinic.exception.SpecialistNotFoundException;
import com.tecsup.petclinic.repositories.SpecialistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpecialistServicelmpl implements SpecialistService {
    SpecialistRepository specialistRepository;

    public SpecialistServicelmpl (SpecialistRepository specialistRepository) { this. specialistRepository = specialistRepository;}

    /**
     *
     * @param specialist
     * @return
     */
    @Override
    public Specialist create(Specialist specialist){return specialistRepository.save(specialist);}

    /**
     *
     * @param specialist
     * @return
     */
    @Override
    public Specialist update(Specialist specialist) {
        return specialistRepository.save(specialist);
    }


    /**
     *
     * @param id
     * @throws SpecialistNotFoundException
     */
    @Override
    public void delete(Integer id) throws SpecialistNotFoundException{

        Specialist specialist = findById(id);
        specialistRepository.delete(specialist);

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Specialist findById(Integer id) throws SpecialistNotFoundException {

        Optional<Specialist> specialist = specialistRepository.findById(id);

        if ( !specialist.isPresent())
            throw new SpecialistNotFoundException("Record not found...!");

        return specialist.get();
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public List<Specialist> findByName(String name) {

        List<Specialist> specialities = specialistRepository.findByName(name);

        specialities.stream().forEach(specialist -> log.info("" + specialist));

        return specialities;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Specialist> findAll() {
        //
        return specialistRepository.findAll();

    }
}
