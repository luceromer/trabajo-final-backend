package com.service;

import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;
import com.model.PacienteDTO;
import com.persistence.entities.Odontologo;
import com.persistence.entities.Paciente;
import com.persistence.entities.Turno;
import com.persistence.repository.OdontologoRepository;
import com.service.implementation.IOdontologoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class OdontologoService implements IOdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper mapper;

    private static final Logger logger = Logger.getLogger(OdontologoService.class.getName());

    public OdontologoService() {
    }

    @Override
    public void crearOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);
        odontologoRepository.save((odontologo));
        logger.info("Se ha guardado el odontologo " + odontologo);
    }

    @Override
    public Set<OdontologoDTO> listarOdontologos() {
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        logger.info("Se ha encontrado una lista de odontólogos.");
        Set<OdontologoDTO> listaOdontologosDTO = new HashSet<>();
        for (Odontologo odo : listaOdontologos) {
            listaOdontologosDTO.add(mapper.map(odo, OdontologoDTO.class));
        }
        return listaOdontologosDTO;
    }

    @Override
    public OdontologoDTO buscarOdontologoPorID(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            Odontologo odontologoEncontrado = odontologoRepository.findById(id).get();
            logger.info("Se ha encontrado el odontologo de id " + id);
            OdontologoDTO odontologoDTO = mapper.map(odontologoEncontrado, OdontologoDTO.class);
            return odontologoDTO;
        } else {
            throw new ResourceNotFoundException("No se ha encontrado un odontólogo correcto.");
        }
    }

    @Override
    public void modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(odontologoDTO.getId())) {
            Odontologo newOdontologo = mapper.map(odontologoDTO, Odontologo.class);
            odontologoRepository.save(newOdontologo);
            logger.info("Se ha guardado el odontologo de id " + newOdontologo.getId());
        } else {
            throw new ResourceNotFoundException("No se encontró el odontólogo a modificar.");
        }

    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
            logger.info("Se ha encontrado un odontólogo con el id " + id + " y se ha eliminado.");
        } else {
            throw new ResourceNotFoundException("No se ha encontrado un odontólogo correcto");
        }
    }
}
	

