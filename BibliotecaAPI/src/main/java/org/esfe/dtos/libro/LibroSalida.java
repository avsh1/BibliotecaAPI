package org.esfe.dtos.libro;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.autor.AutorSalida;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class LibroSalida implements Serializable {
    private Integer id;

    private String titulo;

    private String nombreAutor;

    private LocalDate publicacion;

    private String editorial;

    private String categoria;

    private AutorSalida autor;
}
