package com.reserva.controlador;

import com.reserva.modelo.Espacio;
import com.reserva.servicio.EspacioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/espacios")
public class EspacioController {

    private final EspacioService espacioService;

    public EspacioController(EspacioService espacioService) {
        this.espacioService = espacioService;
    }

    @GetMapping
    public List<Espacio> listarEspacios(@RequestParam(name = "incluirInactivos", defaultValue = "false") boolean incluirInactivos) {
        if (incluirInactivos) {
            return espacioService.listarTodos();
        }
        return espacioService.listarActivos();
    }
}