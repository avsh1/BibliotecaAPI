package org.esfe.servicios.inplementaciones;

import org.esfe.dtos.PrestamoCambiarEstado;
import org.esfe.dtos.prestamo.PrestamoGuardar;
import org.esfe.dtos.prestamo.PrestamoModificar;
import org.esfe.dtos.prestamo.PrestamoSalida;
import org.esfe.modelos.Prestamo;
import org.esfe.repositorios.IPrestamoRepository;
import org.esfe.servicios.interfaces.IPrestamoService;
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
public class PrestamoService implements IPrestamoService {

    @Autowired
    private IPrestamoRepository prestamoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PrestamoSalida> obtenerTodos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();

        return prestamos.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<PrestamoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Prestamo> page = prestamoRepository.findAll(pageable);

        List<PrestamoSalida> prestamosDto = page.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoSalida.class))
                .collect(Collectors.toList());
        return new PageImpl<>(prestamosDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public PrestamoSalida obtenerPorId(Integer id) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);

        if(prestamo.isPresent()){
            return modelMapper.map(prestamo.get(), PrestamoSalida.class);
        }
        return null;
    }

    @Override
    public List<PrestamoSalida> obtenerPorPrestamoId(Integer id) {
        List<Prestamo> prestamos = prestamoRepository.findByPrestamoId(id);

        return prestamos.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public PrestamoSalida crear(PrestamoGuardar prestamoGuardar) {
        Prestamo prestamo = modelMapper.map(prestamoGuardar, Prestamo.class);
        prestamo.setId(null);
        prestamo.setEstado(Prestamo.Status.PRESTADO);

        return modelMapper.map(prestamoRepository.save(prestamo), PrestamoSalida.class);
    }

    @Override
    public PrestamoSalida editar(PrestamoModificar prestamoModificar) {
        Prestamo prestamo = prestamoRepository.save(modelMapper.map(prestamoModificar, Prestamo.class));

        return modelMapper.map(prestamo, PrestamoSalida.class);
    }

    @Override
    public PrestamoSalida cambiarEstado(PrestamoCambiarEstado prestamoCambiarEstado) {
        Prestamo prestamo = prestamoRepository.findById(prestamoCambiarEstado.getId()).get();
        prestamo.setEstado(Prestamo.Status.valueOf(prestamoCambiarEstado.getEstado()));

        return modelMapper.map(prestamoRepository.save(prestamo), PrestamoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        prestamoRepository.deleteById(id);
    }
}
