package org.esfe.servicios.inplementaciones;


import org.modelmapper.ModelMapper;
import org.esfe.dtos.autor.AutorGuardar;
import org.esfe.dtos.autor.AutorModificar;
import org.esfe.dtos.autor.AutorSalida;
import org.esfe.modelos.Autor;
import org.esfe.repositorios.IAutorRepository;
import org.esfe.servicios.interfaces.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService implements IAutorService {

    @Autowired
    private IAutorRepository autorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AutorSalida> obtenerTodos() {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .map(autor -> modelMapper.map(autor, AutorSalida.class))
                .collect(Collectors.toList());

    }

    @Override
    public Page<AutorSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Autor> page = autorRepository.findAll(pageable);

        List<AutorSalida> autoresDto = page.stream()
                .map(autor -> modelMapper.map(autor, AutorSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(autoresDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public AutorSalida obtenerPorId(Integer id) {
        return modelMapper.map(autorRepository.findById(id).get(), AutorSalida.class);
    }

    @Override
    public AutorSalida crear(AutorGuardar autorGuardar) {
        Autor autor = autorRepository.save(modelMapper.map(autorGuardar, Autor.class));
        return modelMapper.map(autor, AutorSalida.class);
    }

    @Override
    public AutorSalida editar(AutorModificar autorModificar) {
        Autor autor = autorRepository.save(modelMapper.map(autorModificar, Autor.class));
        return modelMapper.map(autor, AutorSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        autorRepository.deleteById(id);

    }
}
