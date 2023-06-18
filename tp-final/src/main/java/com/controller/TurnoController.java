package com.controller;

import com.model.Paciente;
import com.model.Turno;
import com.service.OdontologoService;
import com.service.PacienteService;
import com.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public ResponseEntity<List<Turno>> buscarTodos() {
		return ResponseEntity.ok(turnoService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turno> buscar(@PathVariable Integer id) {
		Turno turno = turnoService.buscar(id).orElse(null);
		return ResponseEntity.ok(turno);
	}
	
	@PostMapping
	public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
		ResponseEntity<Turno> respuesta;
		if(pacienteService.buscar(turno.getPaciente().getId()) != null
				&& odontologoService.buscar(turno.getOdontologo().getId()) != null){
			respuesta = ResponseEntity.ok(turnoService.registrarTurno(turno));
		}else{
			respuesta = ResponseEntity.badRequest().build();
		}
		return respuesta;
	}
	
	@PutMapping()
	public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) {
		ResponseEntity<Turno> response = null;
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
