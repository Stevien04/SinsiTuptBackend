package com.reserva.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormularioReservaController {

    @GetMapping("/reservas/formulario")
    public String mostrarFormulario() {
        return "FormularioReserva";
    }
}