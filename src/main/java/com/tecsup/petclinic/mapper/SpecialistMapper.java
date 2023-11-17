package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.domain.SpecialistTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Specialist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SpecialistMapper {
    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);

    //@Mapping(target = "name", source = "name")
    @Mapping(source = "h_close", target = "h_close")
    Specialist toSpecialist(SpecialistTO specialistTO);

    default Date stringToDate(String dateStr) {

        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    @Mapping(source = "h_close", target = "h_close")
    SpecialistTO toSpecialistTO(Specialist specialist);

    default String dateToString(Date date) {

        if (date != null ) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } else {
            return "";
        }

    }

    List<SpecialistTO> toSpecialistList (List<Specialist> specialistList);

    List<Specialist> toSpecialist(List<SpecialistTO> specialistTOList);

}
