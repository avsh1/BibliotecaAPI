package org.esfe.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PrestamoCambiarEstado implements Serializable {
    private Integer id;
    private String estado;
}
