package org.esfe.dtos.libro;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter

public class LibroGuardar implements Serializable  {
    private String titulo;

    private String nombreAutor;

    private LocalDate publicacion;

    private String editorial;

    private String categoria;

    private Integer autorId;
}
