package org.esfe.controladores;

import org.esfe.dtos.autor.AutorGuardar;
import org.esfe.dtos.autor.AutorModificar;
import org.esfe.dtos.autor.AutorSalida;
import org.esfe.servicios.interfaces.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
    @Autowired
    private IAutorService autorService;

    @GetMapping
    public ResponseEntity<Page<AutorSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<AutorSalida> autores = autorService.obtenerTodosPaginados(pageable);
        if(autores.hasContent()){
            return ResponseEntity.ok(autores);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<AutorSalida>> mostrarTodos(){
        List<AutorSalida> autores = autorService.obtenerTodos();
        if(!autores.isEmpty()){
            return ResponseEntity.ok(autores);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorSalida> buscarPorId(@PathVariable Integer id){
        AutorSalida autor = autorService.obtenerPorId(id);

        if(autor != null){
            return ResponseEntity.ok(autor);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AutorSalida> crear(@RequestBody AutorGuardar autorGuardar){
        AutorSalida autor = autorService.crear(autorGuardar);
        return ResponseEntity.ok(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorSalida> editar(@PathVariable Integer id, @RequestBody AutorModificar autorModificar){
        AutorSalida autor = autorService.editar(autorModificar);
        return ResponseEntity.ok(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        autorService.eliminarPorId(id);
        return ResponseEntity.ok("Autor eliminado correctamente");
    }

}
