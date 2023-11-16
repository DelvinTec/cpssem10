package com.tecsup.petclinic.entities;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;


@Entity(name = "specialties")
@Data
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String office;
    private Integer h_open;
    private Integer h_close;

    public Specialist() {
    }

    public Specialist(Integer id, String name, String office, Integer h_open, Integer h_close) {
        super();
        this.id = id;
        this.name = name;
        this.office = office;
        this.h_open = h_open;
        this.h_close = h_close;
    }

    public Specialist(String name, String office, Integer h_open, Integer h_close) {
        super();
        this.name = name;
        this.office = office;
        this.h_open = h_open;
        this.h_close = h_close;
    }
}
