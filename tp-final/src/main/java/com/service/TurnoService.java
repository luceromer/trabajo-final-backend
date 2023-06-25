package com.service;
import com.config.exception.ResourceNotFoundException;
import com.model.TurnoDTO;
import com.persistence.entities.Turno;
import com.persistence.repository.OdontologoRepository;
import com.persistence.repository.PacienteRepository;
import com.persistence.repository.TurnoRepository;
import com.service.implementation.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class TurnoService implements ITurnoService {
	@Autowired
	private TurnoRepository turnoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private OdontologoRepository odontologoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	private static final Logger logger = Logger.getLogger(TurnoService.class.getName());
	public TurnoService() {
		this.turnoRepository = turnoRepository;
	}
	
	@Override
	public void crearTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
		Turno turno = mapper.map(turnoDTO, Turno.class);
		if(pacienteRepository.existsById(turno.getPaciente().getId()) || odontologoRepository.existsById(turno.getOdontologo().getId())) {
			turnoRepository.save(turno);
			logger.info("Se ha creado el turno con el id " + turno.getId());
		} else {
			throw new ResourceNotFoundException("El paciente o el odontólogo no existen. Por favor cree primero los registros.");
		}
	}
	
	@Override
	public Set<TurnoDTO> listarTurnos() {
		List<Turno> listaTurnos = turnoRepository.findAll();
		Set<TurnoDTO> listaTurnosDTO = new HashSet<>();
		for(Turno tur:listaTurnos) {
			listaTurnosDTO.add(mapper.map(tur, TurnoDTO.class));
		}
		return listaTurnosDTO;
	}
	
	@Override
	public TurnoDTO buscarTurnoPorID(Long id) throws ResourceNotFoundException {
		if(turnoRepository.existsById(id)) {
			Turno turnoEncontrado = turnoRepository.findById(id).get();
			TurnoDTO turnoDTO = mapper.map(turnoEncontrado, TurnoDTO.class);
			return turnoDTO;
		} else {
			throw new ResourceNotFoundException("El id no es válido.");
		}
	}
	
	@Override
	public void modificarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
		if (turnoRepository.existsById(turnoDTO.getId())) {
			Turno turno = mapper.map(turnoDTO, Turno.class);
			turnoRepository.save(turno);
			logger.info("Se ha modificado el turno con el id " + turno.getId());}
		else {
			throw new ResourceNotFoundException("No se encontró el paciente a modificar.");
		}
	}
	
	@Override
	public void eliminarTurno(Long id) throws ResourceNotFoundException {
		if (turnoRepository.existsById(id)) {
			turnoRepository.deleteById(id);
			logger.info("Se ha eliminado el turno con el id " + id);
		} else {
			throw new ResourceNotFoundException("No se ha encontrado un turno correcto");
		}
	}
}
