package org.esfe.servicios.inplementaciones;

import org.esfe.dtos.libro.LibroGuardar;
import org.esfe.dtos.libro.LibroModificar;
import org.esfe.dtos.libro.LibroSalida;
import org.esfe.modelos.Libro;
import org.esfe.repositorios.ILibroRepository;
import org.esfe.servicios.interfaces.ILibroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService implements ILibroService {

    @Autowired
    private ILibroRepository libroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LibroSalida> obtenerTodos() {
        List<Libro> libros = libroRepository.findAll();

        return libros.stream()
                .map(libro -> modelMapper.map(libro, LibroSalida.class))
                .collect(Collectors.toList());    }

    @Override
    public Page<LibroSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Libro> page = libroRepository.findAll(pageable);

        List<LibroSalida> librosDto = page.stream()
                .map(libro -> modelMapper.map(libro, LibroSalida.class))
                .collect(Collectors.toList());
        return new PageImpl<>(librosDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public LibroSalida obtenerPorId(Integer id) {
        Optional<Libro> libro = libroRepository.findById(id);

        if(libro.isPresent()){
            return modelMapper.map(libro.get(), LibroSalida.class);
        }
        return null;
    }

    @Override
    public LibroSalida crear(LibroGuardar libroGuardar) {
        Libro libro = modelMapper.map(libroGuardar, Libro.class);
        libro.setId(null);

        return modelMapper.map(libroRepository.save(libro), LibroSalida.class);
    }

    @Override
    public LibroSalida editar(LibroModificar libroModificar) {
            Libro proyecto = libroRepository.save(modelMapper.map(libroModificar, Libro.class));

            return modelMapper.map(proyecto, LibroSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        libroRepository.deleteById(id);
    }
}
