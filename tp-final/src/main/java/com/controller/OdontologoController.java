package com.controller;

import com.config.exception.BadRequestException;
import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;
import com.model.PacienteDTO;
import com.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
	private static final Logger logger = Logger.getLogger(OdontologoService.class.getName());
	
	@Autowired
	private OdontologoService odontologoService;
	
	@GetMapping
	public ResponseEntity<Set<OdontologoDTO>> buscarTodos(){
		logger.info("Petición GET - Buscar todos los odontólogos");
		return ResponseEntity.ok(odontologoService.listarOdontologos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OdontologoDTO> buscar(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
		logger.info("Petición GET - Buscar odontólogo con id " + id);
		OdontologoDTO odontologoDTO = odontologoService.buscarOdontologoPorID(id);
		return ResponseEntity.ok(odontologoDTO);
	}
	
	@PostMapping()
	public ResponseEntity<String> registrarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
		logger.info("Petición POST - Crear nuevo odontólogo");
		odontologoService.crearOdontologo(odontologoDTO);
		return ResponseEntity.ok("Odontólogo creado");
	}
	
	
	@PutMapping()
	public ResponseEntity<String> actualizar(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
		logger.info("Petición PUT - Modificar odontólogo " + odontologoDTO.getId());
		odontologoService.modificarOdontologo(odontologoDTO);
			return ResponseEntity.ok("Odontólogo modificado con éxito.");
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("Petición DELETE - Borrar odontólogo " + id);
			odontologoService.eliminarOdontologo(id);
		return ResponseEntity.ok("Odontólogo con id " + id + "eliminado exitosamente.");
	}
	
}
