package com.controller;

import com.config.exception.BadRequestException;
import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;
import com.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
	
	@Autowired
	private OdontologoService odontologoService;
	
	@GetMapping
	public ResponseEntity<Set<OdontologoDTO>> buscarTodos(){
		return ResponseEntity.ok(odontologoService.listarOdontologos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OdontologoDTO> buscar(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
		OdontologoDTO odontologoDTO = odontologoService.buscarOdontologoPorID(id);
		return ResponseEntity.ok(odontologoDTO);
	}
	
	@PostMapping()
	public ResponseEntity<String> registrarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
		odontologoService.crearOdontologo(odontologoDTO);
		return ResponseEntity.ok("Odontólogo creado");
	}
	
	
	@PutMapping()
	public ResponseEntity<String> actualizar(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
		ResponseEntity<String> response = null;
		if (odontologoDTO.getId() != null) {
			odontologoService.modificarOdontologo(odontologoDTO);
			response = ResponseEntity.ok("Odontólogo modificado con éxito.");
		}
		else
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
			odontologoService.eliminarOdontologo(id);
		return ResponseEntity.ok("Todo ok");
	}
	
}
