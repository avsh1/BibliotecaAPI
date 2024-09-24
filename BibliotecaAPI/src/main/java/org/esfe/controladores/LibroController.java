package org.esfe.controladores;

import org.esfe.dtos.libro.LibroGuardar;
import org.esfe.dtos.libro.LibroModificar;
import org.esfe.dtos.libro.LibroSalida;
import org.esfe.servicios.interfaces.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/proyectos")
public class LibroController {
    @Autowired
    private ILibroService libroService;

    @GetMapping
    public ResponseEntity<Page<LibroSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<LibroSalida> libros = libroService.obtenerTodosPaginados(pageable);

        if(libros.hasContent()){
            return ResponseEntity.ok(libros);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<LibroSalida>> mostrarTodos(){
        List<LibroSalida> libros = libroService.obtenerTodos();

        if(!libros.isEmpty()){
            return ResponseEntity.ok(libros);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroSalida> mostrarPorId(@PathVariable Integer id){
        LibroSalida libro = libroService.obtenerPorId(id);

        if(libro != null){
            return ResponseEntity.ok(libro);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LibroSalida> crear(@RequestBody LibroGuardar libroGuardar){
        LibroSalida libro = libroService.crear(libroGuardar);

        if(libro != null){
            return ResponseEntity.ok(libro);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroSalida> editar(@PathVariable Integer id, @RequestBody LibroModificar libroModificar){
        LibroSalida libro = libroService.editar(libroModificar);

        if(libro != null){
            return ResponseEntity.ok(libro);
        }

        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        libroService.eliminarPorId(id);
        return ResponseEntity.ok("Libro eliminado correctamente");
    }
}

