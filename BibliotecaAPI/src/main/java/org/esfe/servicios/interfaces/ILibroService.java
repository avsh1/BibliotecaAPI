package org.esfe.servicios.interfaces;

import org.esfe.dtos.libro.LibroGuardar;
import org.esfe.dtos.libro.LibroModificar;
import org.esfe.dtos.libro.LibroSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILibroService {
    List<LibroSalida> obtenerTodos();

    Page<LibroSalida> obtenerTodosPaginados(Pageable pageable);

    LibroSalida obtenerPorId(Integer id);

    LibroSalida crear(LibroGuardar libroGuardar);

    LibroSalida editar(LibroModificar libroModificar);

    void eliminarPorId(Integer id);
}
