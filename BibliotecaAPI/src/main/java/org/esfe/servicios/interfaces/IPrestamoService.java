package org.esfe.servicios.interfaces;

import org.esfe.dtos.PrestamoCambiarEstado;
import org.esfe.dtos.prestamo.PrestamoGuardar;
import org.esfe.dtos.prestamo.PrestamoModificar;
import org.esfe.dtos.prestamo.PrestamoSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPrestamoService {
    List<PrestamoSalida> obtenerTodos();
    Page<PrestamoSalida> obtenerTodosPaginados(Pageable pageable);
    PrestamoSalida obtenerPorId(Integer id);
    List<PrestamoSalida> obtenerPorPrestamoId(Integer id);
    PrestamoSalida crear(PrestamoGuardar prestamoGuardar);
    PrestamoSalida editar(PrestamoModificar prestamoModificar);
    PrestamoSalida cambiarEstado(PrestamoCambiarEstado prestamoCambiarEstado);
    void eliminarPorId(Integer id);
}
