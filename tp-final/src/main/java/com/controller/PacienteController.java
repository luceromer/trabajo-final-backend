package com.controller;

import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;
import com.model.PacienteDTO;
import com.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    private static final Logger logger = Logger.getLogger(PacienteController.class.getName());

    @GetMapping()
    public ResponseEntity<Set<PacienteDTO>> buscarTodos() {
        logger.info("Petición GET - Buscar todos los pacientes");
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Petición GET - Buscar paciente con id " + id);
        PacienteDTO pacienteDTO = pacienteService.buscarPacientePorID(id);
        logger.info(pacienteDTO.toString());
        return ResponseEntity.ok(pacienteDTO);
    }

    @PostMapping()
    public ResponseEntity<String> registrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        logger.info("Petición POST - Crear nuevo paciente");
        pacienteService.crearPaciente(pacienteDTO);
        return ResponseEntity.ok("Paciente registrado correctamente.");
    }

    @PutMapping()
    public ResponseEntity<String> actualizar(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        logger.info("Petición PUT - Modificar paciente " + pacienteDTO.getId());
        if (pacienteDTO.getId() != null) {
            PacienteDTO pacienteAModificar = pacienteService.buscarPacientePorID(pacienteDTO.getId());
            pacienteService.modificarPaciente(pacienteDTO);
            return ResponseEntity.ok("Paciente modificado.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Petición DELETE - Borrar paciente " + id);
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
    }
}
