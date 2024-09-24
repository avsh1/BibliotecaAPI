package org.esfe.modelos;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombrelibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;


    @Enumerated(EnumType.STRING)
    private Status estado;

    @ManyToMany
    @JoinTable(
            name = "prestamo_libro",
            joinColumns = @JoinColumn(name = "prestamo_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros;

    public  static enum  Status{
        ENTREGADO, RETRASADO, PRESTADO
    }

}
