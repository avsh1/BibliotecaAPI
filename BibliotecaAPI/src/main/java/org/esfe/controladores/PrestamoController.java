package org.esfe.controladores;

import org.esfe.dtos.PrestamoCambiarEstado;
import org.esfe.dtos.prestamo.PrestamoGuardar;
import org.esfe.dtos.prestamo.PrestamoModificar;
import org.esfe.dtos.prestamo.PrestamoSalida;
import org.esfe.servicios.interfaces.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private IPrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<Page<PrestamoSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<PrestamoSalida> prestamos = prestamoService.obtenerTodosPaginados(pageable);

        if(prestamos.hasContent()){
            return ResponseEntity.ok(prestamos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PrestamoSalida>> mostrarTodos(){
        List<PrestamoSalida> prestamos = prestamoService.obtenerTodos();

        if(!prestamos.isEmpty()){
            return ResponseEntity.ok(prestamos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoSalida> mostrarPodId(@PathVariable Integer id){
        PrestamoSalida prestamo = prestamoService.obtenerPorId(id);

        if(prestamo != null){
            return ResponseEntity.ok(prestamo);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/proyecto/{id}")
    public ResponseEntity<List<PrestamoSalida>> mostrarPorPrestamo(@PathVariable Integer id){
        List<PrestamoSalida> prestamos = prestamoService.obtenerPorPrestamoId(id);

        if(!prestamos.isEmpty()){
            return ResponseEntity.ok(prestamos);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PrestamoSalida> crear(@RequestBody PrestamoGuardar prestamoGuardar){
        PrestamoSalida prestamo = prestamoService.crear(prestamoGuardar);

        if(prestamo != null){
            return ResponseEntity.ok(prestamo);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoSalida> editar(@PathVariable Integer id,  @RequestBody PrestamoModificar prestamoModificar){
        PrestamoSalida prestamo = prestamoService.editar(prestamoModificar);

        if(prestamo != null){
            return ResponseEntity.ok(prestamo);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PatchMapping
    public ResponseEntity<PrestamoSalida> cambiarEstado(@RequestBody PrestamoCambiarEstado prestamoCambiarEstado){
        PrestamoSalida pretamo = prestamoService.cambiarEstado(prestamoCambiarEstado);

        if(pretamo != null){
            return ResponseEntity.ok(pretamo);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        prestamoService.eliminarPorId(id);
        return ResponseEntity.ok(" prestamo eliminada correctamente");
    }

}
