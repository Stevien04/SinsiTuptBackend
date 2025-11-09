package com.reserva.servicio;

import com.reserva.modelo.Espacio;
import com.reserva.repositorio.EspacioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EspacioService {

    private final EspacioRepository espacioRepository;

    public EspacioService(EspacioRepository espacioRepository) {
        this.espacioRepository = espacioRepository;
    }

    public List<Espacio> listarActivos() {
        return new ArrayList<>(espacioRepository.findByEstadoOrderByNombreAsc(1));
    }

    public List<Espacio> listarTodos() {
        return new ArrayList<>(espacioRepository.findAllOrdenados());
    }

    public Optional<Espacio> buscarPorId(Integer id) {
        return espacioRepository.findById(id);
    }
}