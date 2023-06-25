package com.service;
import com.controller.exception.ResourceNotFoundException;
import com.model.PacienteDTO;
import com.persistence.entities.Domicilio;
import com.persistence.entities.Paciente;
import com.persistence.repository.DomicilioRepository;
import com.persistence.repository.PacienteRepository;
import com.service.implementation.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	private ModelMapper mapper;
	
	public PacienteService(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	
	@Override
	public void crearPaciente(PacienteDTO pacienteDTO) {
		Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
		Domicilio domicilio = mapper.map(pacienteDTO.getDomicilio(), Domicilio.class);
		pacienteRepository.save(paciente);
		domicilioRepository.save(domicilio);
	}
	
	@Override
	public Set<PacienteDTO> listarPacientes() {
		List<Paciente> listaPacientes = pacienteRepository.findAll();
		Set<PacienteDTO> listaPacientesDTO = new HashSet<>();
		for(Paciente pac:listaPacientes) {
			listaPacientesDTO.add(mapper.map(pac, PacienteDTO.class));
		}
		return listaPacientesDTO;
	}
	
	@Override
	public PacienteDTO buscarPacientePorID(Long id) throws ResourceNotFoundException {
		if (pacienteRepository.existsById(id)) {
			Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
			PacienteDTO pacienteDTO =  mapper.map(pacienteEncontrado, PacienteDTO.class);
			return pacienteDTO;
		} else {
			throw new ResourceNotFoundException("No se ha encontrado un paciente correcto.");
		}
	}
	
	@Override
	public void modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
		if (pacienteRepository.existsById(pacienteDTO.getId())) {
		Paciente newPaciente = mapper.map(pacienteDTO, Paciente.class);
		pacienteRepository.save(newPaciente);}
		else {
			throw new ResourceNotFoundException("No se encontró el paciente a modificar.");
		}
	}
	
	@Override
	public void eliminarPaciente(Long id) throws ResourceNotFoundException {
			if (pacienteRepository.existsById(id)) {
			pacienteRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException("No se ha encontrado un paciente correcto");
			}
	}
}
