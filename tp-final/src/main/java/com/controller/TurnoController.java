package com.controller;

import com.controller.exception.ResourceNotFoundException;
import com.model.TurnoDTO;
import com.persistence.entities.Turno;
import com.service.OdontologoService;
import com.service.PacienteService;
import com.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private OdontologoService odontologoService;
	
	
	@GetMapping
	public ResponseEntity<Set<TurnoDTO>> buscarTodos() {
		return ResponseEntity.ok(turnoService.listarTurnos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException {
		TurnoDTO turnoEncontradoDTO = turnoService.buscarTurnoPorID(id);
		return ResponseEntity.ok(turnoEncontradoDTO);
	}
	
	@PostMapping
		public ResponseEntity<String> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
		 turnoService.crearTurno(turnoDTO);
			return ResponseEntity.ok("Todo ok");
	}
	
	@PutMapping()
	public ResponseEntity<Optional<Turno>> actualizar(@RequestBody TurnoDTO turnoDTO) {
	return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
	return null;
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> generalExceptionHandler(Exception ex) {
		System.out.println("Ha ocurrido un error: " + ex.getMessage());
		return ResponseEntity.internalServerError().body("Ha ocurrido un error. Por favor intentar más tarde");
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		System.out.println("Ha ocurrido un error: " + ex.getMessage());
		return ResponseEntity.badRequest().body("Hubo un error: Odontólogo y/o Paciente inexistentes.");
	}
	
	
	
}
