package com.controller;

import com.config.exception.ResourceNotFoundException;
import com.model.TurnoDTO;
import com.service.OdontologoService;
import com.service.PacienteService;
import com.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private OdontologoService odontologoService;
	private static final Logger logger = Logger.getLogger(PacienteController.class.getName());

	@GetMapping("/{id}")
	public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("Petición GET - Buscar turno con id " + id);
		TurnoDTO turnoEncontradoDTO = turnoService.buscarTurnoPorID(id);
		return ResponseEntity.ok(turnoEncontradoDTO);
	}
	
	@GetMapping()
	public ResponseEntity<Set<TurnoDTO>> buscarTodosLosTurnosOPorPaciente(@RequestParam(name="paciente_id", required= false) Long id) throws ResourceNotFoundException {
			if(id == null) {
				logger.info("Petición GET - Buscar todos los turnos");
				return ResponseEntity.ok(turnoService.listarTurnos());
			} else {
				logger.info("Petición GET - Buscar todos los turnos del paciente " + id);
				Set<TurnoDTO> turnosEncontradosDTO = turnoService.buscarTurnosPorPaciente(id);
				return ResponseEntity.ok(turnosEncontradosDTO);
			}
		}
	
	
	@PostMapping
		public ResponseEntity<String> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
		logger.info("Petición POST - Crear turno");
		turnoService.crearTurno(turnoDTO);
			return ResponseEntity.ok("Turno creado exitosamente.");
	}
	
	@PutMapping
	public ResponseEntity<String> actualizar(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
		logger.info("Petición PUT - Modificar turno " + turnoDTO.getId());
		turnoService.modificarTurno(turnoDTO);
		return ResponseEntity.ok("Turno " + turnoDTO.getId() + "modificado.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		logger.info("Petición DELETE - Eliminar turno " + id);
	return null;
	}
	
}
