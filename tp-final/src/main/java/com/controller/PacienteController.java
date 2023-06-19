package com.controller;

import com.model.PacienteDTO;
import com.persistence.entities.Paciente;
import com.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping()
	public ResponseEntity<Optional<List<Paciente>>> buscarTodos(){
		return ResponseEntity.ok(pacienteService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Paciente>> buscar(@PathVariable Integer id) {
		Optional<Paciente> paciente = Optional.ofNullable(pacienteService.buscar(id).orElse(null));
		return ResponseEntity.ok(paciente);
	}
	
	@PostMapping()
	public ResponseEntity<Optional<Paciente>> registrarPaciente(@RequestBody Paciente paciente) {
		return ResponseEntity.ok(pacienteService.guardar(paciente));
	}
	
	@PutMapping()
	public ResponseEntity<Optional<Paciente>> actualizar(@RequestBody Paciente paciente) {
		ResponseEntity<Optional<Paciente>> response = null;
		if (paciente.getId() != null && pacienteService.buscar(paciente.getId()).isPresent())
			response = ResponseEntity.ok(pacienteService.actualizar(paciente));
		else
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		ResponseEntity<String> response = null;
		
		if (pacienteService.buscar(id).isPresent()) {
			pacienteService.eliminar(id);
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return response;
	}
}
