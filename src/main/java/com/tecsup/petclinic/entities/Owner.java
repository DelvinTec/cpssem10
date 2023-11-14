package com.tecsup.petclinic.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String first;
    @Column(name = "last_name")
    private String last;
    private String address;
    private String city;
    @Column(name = "telephone")
    private Long telephone;


    public Owner() {
    }

    public Owner(Integer id, String first_name, String last_name, String address, String city, Long telephone) {
        super();
        this.id = id;
        this.first = first_name;
        this.last = last_name;
        this.address = address;
        this.city = city;
        this.telephone = telephone;

    }

    public Owner(String first_name, String last_name, String address, String city, Long telephone) {
        super();
        this.first = first_name;
        this.last = last_name;
        this.address = address;
        this.city = city;
        this.telephone = telephone;

    }

}
