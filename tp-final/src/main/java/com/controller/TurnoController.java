package com.controller;

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
	public ResponseEntity<Optional<List<Turno>>> buscarTodos() {
		return ResponseEntity.ok(turnoService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Turno>> buscar(@PathVariable Integer id) {
		Optional<Turno> turno = Optional.ofNullable(turnoService.buscar(id).orElse(null));
		return ResponseEntity.ok(turno);
	}
	
	@PostMapping
		public ResponseEntity<Optional<Turno>> registrarTurno(@RequestBody Turno turno) {
		ResponseEntity<Optional<Turno>> respuesta;
		if(pacienteService.buscar(turno.getPaciente().getId()) != null
				&& odontologoService.buscar(turno.getOdontologo().getId()) != null){
			respuesta = ResponseEntity.ok(turnoService.registrarTurno(turno));
		}else{
			respuesta = ResponseEntity.badRequest().build();
		}
		return respuesta;
	}
	
	@PutMapping()
	public ResponseEntity<Optional<Turno>> actualizar(@RequestBody Turno turno) {
		ResponseEntity<Optional<Turno>> response = null;
		if (turno.getId() != null && turnoService.buscar(turno.getId()).isPresent())
			response = ResponseEntity.ok(turnoService.actualizar(turno));
		else
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		ResponseEntity<String> response = null;
		if (turnoService.buscar(id).isPresent()) {
			turnoService.eliminar(id);
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return response;
	}
	
}
