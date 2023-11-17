package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecialistTO {

    private Integer id;

    private String name;

    private String office;

    private int h_open;

    private String h_close;
}
