package org.esfe.servicios.interfaces;

import org.esfe.dtos.autor.AutorGuardar;
import org.esfe.dtos.autor.AutorModificar;
import org.esfe.dtos.autor.AutorSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAutorService {
    List<AutorSalida> obtenerTodos();

    Page<AutorSalida> obtenerTodosPaginados(Pageable pageable);

    AutorSalida obtenerPorId(Integer id);

    AutorSalida crear(AutorGuardar autorGuardar);

    AutorSalida editar(AutorModificar autorModificar);

    void eliminarPorId(Integer id);
}
