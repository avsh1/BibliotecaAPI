package org.esfe.dtos.autor;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AutorGuardar implements Serializable {
    private String nombre;
    private String nacionalidad;
}
