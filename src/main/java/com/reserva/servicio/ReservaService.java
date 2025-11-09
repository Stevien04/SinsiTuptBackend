package com.reserva.servicio;

import com.reserva.modelo.Reserva;
import com.reserva.repositorio.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Integer id) {
        return reservaRepository.findById(id);
    }

    public Reserva crearReserva(Reserva reserva) {
        if (reserva.getEstado() == null || reserva.getEstado().isBlank()) {
            reserva.setEstado("Pendiente");
        }
        return reservaRepository.save(reserva);
    }

    public Reserva actualizarReserva(Integer id, Reserva datosReserva) {
        return reservaRepository.findById(id)
                .map(reservaExistente -> {
                    reservaExistente.setUsuarioId(datosReserva.getUsuarioId());
                    reservaExistente.setEspacioId(datosReserva.getEspacioId());
                    reservaExistente.setBloqueId(datosReserva.getBloqueId());
                    reservaExistente.setCursoId(datosReserva.getCursoId());
                    reservaExistente.setFechaReserva(datosReserva.getFechaReserva());
                    reservaExistente.setDescripcionUso(datosReserva.getDescripcionUso());
                    reservaExistente.setCantidadEstudiantes(datosReserva.getCantidadEstudiantes());
                    if (datosReserva.getEstado() != null && !datosReserva.getEstado().isBlank()) {
                        reservaExistente.setEstado(datosReserva.getEstado());
                    }
                    return reservaRepository.save(reservaExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con el id proporcionado"));
    }

    public void eliminarReserva(Integer id) {
        if (!reservaRepository.existsById(id)) {
            throw new IllegalArgumentException("Reserva no encontrada con el id proporcionado");
        }
        reservaRepository.deleteById(id);}
    }