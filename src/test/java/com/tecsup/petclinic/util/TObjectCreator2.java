package com.tecsup.petclinic.util;

import com.tecsup.petclinic.domain.SpecialistTO;
import com.tecsup.petclinic.entities.Specialist;
import com.tecsup.petclinic.entities.Specialist;

import java.util.ArrayList;
import java.util.List;

public class TObjectCreator2 {

    public static Specialist getSpecialist() {
        return new Specialist(1,"Leo","Hola",1, null);
    }

    public static Specialist newSpecialist() {
        return new Specialist(0,"Punky","hola",1, null);
    }

    public static Specialist newSpecialistCreated() {
        Specialist specialist = newSpecialist();
        specialist.setId(1000);
        return specialist;
    }

    public static Specialist newSpecialistForUpdate() {
        return new Specialist(0,"Bear","Hola",1,null);
    }

    public static Specialist newSpecialistCreatedForUpdate() {
        Specialist specialist = newSpecialistForUpdate();
        specialist.setId(4000);
        return specialist;
    }

    public static Specialist newSpecialistForDelete() {
        return new Specialist(0,"Bird","Adios",1, null);
    }

    public static Specialist newPetCreatedForDelete() {
        Specialist specialist = newSpecialistForDelete();
        specialist.setId(2000);
        return specialist;
    }
    public static List<SpecialistTO> getAllSpecialistTOs() {
        List<SpecialistTO> specialistTOS  = new ArrayList<SpecialistTO>();
        specialistTOS.add(new SpecialistTO(1,"Leo","uno",1, "2000-09-07"));
        specialistTOS.add(new SpecialistTO(2,"Basil","dos",2, "2002-08-06"));
        specialistTOS.add(new SpecialistTO(3,"Rosy","tres",3, "2001-04-17"));
        specialistTOS.add(new SpecialistTO(4,"Jewel","cuatro",3, "2000-03-07"));
        specialistTOS.add(new SpecialistTO(5,"Iggy","cinco",4, "2000-11-30"));
        return specialistTOS;
    }


    public static List<Specialist> getSpecialistsForFindByName() {
        List<Specialist> specialists  = new ArrayList<Specialist>();
        specialists.add(new Specialist(1,"Leo","quince",1, null));
        return specialists;
    }

    public static List<Specialist> geSpecialistsForFindByTypeId() {
        List<Specialist> specialists  = new ArrayList<Specialist>();
        specialists.add(new Specialist(9,"Lucky","como",7, null));
        specialists.add(new Specialist(11,"Freddy","cuando",9, null));
        return specialists;
    }

    public static List<Specialist> getPetsForFindByOwnerId() {
        List<Specialist> specialists  = new ArrayList<Specialist>();
        specialists.add(new Specialist(12,"Lucky","como etas",10, null));
        specialists.add(new Specialist(13,"Sly","que hubo",10, null));
        return specialists;
    }

    public static SpecialistTO getSpecialistTO() {
        return new SpecialistTO(1,"Leo","cambio",1, "2000-09-07");
    }

    public static SpecialistTO newSpecialistTO() {
        return new SpecialistTO(-1,"Beethoven","hola",1, "2020-05-20");
    }

    public static SpecialistTO newSpecialistTOForDelete() {
        return new SpecialistTO(10000,"Beethoven3","holaaa",1, "2020-05-20");
    }
}
