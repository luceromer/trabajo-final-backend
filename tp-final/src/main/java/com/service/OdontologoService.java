package com.service;

import com.controller.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.OdontologoDTO;
import com.model.PacienteDTO;
import com.persistence.entities.Odontologo;
import com.persistence.entities.Paciente;
import com.persistence.repository.OdontologoRepository;
import com.service.implementation.IOdontologoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
	public class OdontologoService implements IOdontologoService {
	
	@Autowired
	private OdontologoRepository odontologoRepository;
	private static final Logger newLogger = LogManager.getLogger(OdontologoService.class);
	@Autowired
	private ModelMapper mapper;
	
	public OdontologoService() {
	}
	
	@Override
	public void crearOdontologo(OdontologoDTO odontologoDTO) {
		Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);
		odontologoRepository.save((odontologo));
	}
	
	@Override
	public Set<OdontologoDTO> listarOdontologos() {
		List<Odontologo> listaOdontologos = odontologoRepository.findAll();
		Set<OdontologoDTO> listaOdontologosDTO = null;
		for(Odontologo odo:listaOdontologos) {
			listaOdontologosDTO.add(mapper.map(odo, OdontologoDTO.class));
		}
		return listaOdontologosDTO;
	}
	
	@Override
	public OdontologoDTO buscarOdontologoPorID(Long id) throws ResourceNotFoundException {
		if (odontologoRepository.existsById(id)) {
			Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
			OdontologoDTO odontologoDTO =  mapper.map(odontologoEncontrado, OdontologoDTO.class);
			return odontologoDTO;
		} else {
			throw new ResourceNotFoundException("No se ha encontrado un paciente correcto.");
		}
	}
	
	@Override
	public void modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
		if (odontologoRepository.existsById(odontologoDTO.getId())) {
			Odontologo newOdontologo = mapper.map(odontologoDTO, Odontologo.class);
			odontologoRepository.save(newOdontologo);}
		else {
			throw new ResourceNotFoundException("No se encontró el paciente a modificar.");
		}
		
	}
	
	@Override
	public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
		if (odontologoRepository.existsById(id)) {
			odontologoRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("No se ha encontrado un odontólogo correcto");
		}
	}
	
	}
	

