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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-mm-dd");

    public TurnoService() {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public void crearTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        Turno turno = mapper.map(turnoDTO, Turno.class);
        if (pacienteRepository.existsById(turno.getPaciente().getId()) || odontologoRepository.existsById(turno.getOdontologo().getId())) {
            turnoRepository.save(turno);
            turno.getPaciente().addTurnoToListaDeTurnos(turno);
            turno.getOdontologo().addTurnoToListaDeTurnos(turno);
            logger.info("Se ha creado el turno con el id " + turno.getId());
        } else {
            throw new ResourceNotFoundException("El paciente o el odontólogo no existen. Por favor cree primero los registros.");
        }
    }

    @Override
    public Set<TurnoDTO> listarTurnos() {
        List<Turno> listaTurnos = turnoRepository.findAll();
        Set<TurnoDTO> listaTurnosDTO = new HashSet<>();
        for (Turno tur : listaTurnos) {
            listaTurnosDTO.add(mapper.map(tur, TurnoDTO.class));
        }
        return listaTurnosDTO;
    }

    @Override
    public TurnoDTO buscarTurnoPorID(Long id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            Turno turnoEncontrado = turnoRepository.findById(id).get();
            logger.info("Se ha encontrado un turno con el id " + id);
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
            logger.info("Se ha modificado el turno con el id " + turno.getId());
        } else {
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

    @Override
    public Set<TurnoDTO> buscarTurnosPorPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.existsById(id)) {
            Set<Turno> turnosDePaciente = turnoRepository.buscarTurnosPorPaciente(id);
            Set<TurnoDTO> turnosDTODePaciente = new HashSet<>();
            for (Turno t : turnosDePaciente) {
                turnosDTODePaciente.add(mapper.map(t, TurnoDTO.class));
            }
            return turnosDTODePaciente;
        } else {
            throw new ResourceNotFoundException("No se ha encontrado un paciente correcto");
        }
    }

    @Override
    public Set<TurnoDTO> buscarTurnosPorFecha(String date) throws ParseException {
        Date newDate = dateFormatter.parse(date);
        Set<Turno> turnosDePaciente = turnoRepository.buscarTurnosPorFecha(newDate);
        Set<TurnoDTO> turnosDTODePaciente = new HashSet<>();
        for (Turno t : turnosDePaciente) {
            turnosDTODePaciente.add(mapper.map(t, TurnoDTO.class));
        }
        return turnosDTODePaciente;
    }

    @Override
    public Set<TurnoDTO> buscarTurnosPorPacienteYFecha(Long id, String date) throws ParseException {
        Date newDate = dateFormatter.parse(date);
        Set<Turno> turnosPorFecha = turnoRepository.buscarTurnosPorFecha(newDate);
        Set<TurnoDTO> turnosDTOPorFechaYPaciente = new HashSet<>();
        for (Turno t : turnosPorFecha) {
            if (t.getPaciente().getId().equals(id)) {
                turnosDTOPorFechaYPaciente.add(mapper.map(t, TurnoDTO.class));
            }
        }
        return turnosDTOPorFechaYPaciente;
    }
}
