package com.controller;

import com.controller.exception.ResourceNotFoundException;
import com.model.PacienteDTO;
import com.persistence.entities.Paciente;
import com.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping()
	public ResponseEntity<Set<PacienteDTO>> buscarTodos(){
		return ResponseEntity.ok(pacienteService.listarPacientes());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException {
		PacienteDTO pacienteDTO = pacienteService.buscarPacientePorID(id);
		return ResponseEntity.ok(pacienteDTO);
	}
	
	@PostMapping()
	public ResponseEntity<String> registrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
		pacienteService.crearPaciente(pacienteDTO);
		return ResponseEntity.ok("Paciente eliminado correctamente.");
	}
	
	@PutMapping()
	public ResponseEntity<String> actualizar(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
		if (pacienteDTO.getId() != null) {
			PacienteDTO pacienteAModificar = pacienteService.buscarPacientePorID(pacienteDTO.getId());
			pacienteService.modificarPaciente(pacienteDTO);
			return ResponseEntity.ok("Paciente modificado.");
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
			pacienteService.eliminarPaciente(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
	}
}
