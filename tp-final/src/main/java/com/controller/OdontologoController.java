package com.controller;

import com.model.OdontologoDTO;
import com.persistence.entities.Odontologo;
import com.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
	
	@Autowired
	private OdontologoService odontologoService;
	
	@GetMapping
	public ResponseEntity<Optional<List<Odontologo>>> buscarTodos(){
		return ResponseEntity.ok(odontologoService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Odontologo> buscar(@PathVariable Integer id) {
		Optional<Odontologo> optionalDeOdontologo = odontologoService.buscar(id);
		Odontologo odontologo = optionalDeOdontologo.orElse(null);
		return ResponseEntity.ok(odontologo);
	}
	
	@PostMapping()
	public ResponseEntity<Optional<Odontologo>> registrarOdontologo(@RequestBody Odontologo odontologo) {
		return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
	}
	
	
	@PutMapping()
	public ResponseEntity<Optional<Odontologo>> actualizar(@RequestBody Odontologo odontologo) {
		ResponseEntity<Optional<Odontologo>> response = null;
		
		if (odontologo.getId() != null && odontologoService.buscar(odontologo.getId()).isPresent())
			response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
		else
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		ResponseEntity<String> response = null;
		
		if (odontologoService.buscar(id).isPresent()) {
			odontologoService.eliminar(id);
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return response;
	}
	

	
}
