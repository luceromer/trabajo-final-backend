package com.service.implementation;

import com.config.exception.ResourceNotFoundException;
import com.model.TurnoDTO;

import java.util.Set;

public interface ITurnoService {
	
	void crearTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException;
	Set<TurnoDTO> listarTurnos();
	TurnoDTO buscarTurnoPorID(Long id) throws ResourceNotFoundException;
	
	Set<TurnoDTO> buscarTurnosPorPaciente(Long id) throws ResourceNotFoundException;
	void modificarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException;
	void eliminarTurno(Long id) throws ResourceNotFoundException;
	
	
}
