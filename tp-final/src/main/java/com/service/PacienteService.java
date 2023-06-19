package com.service;
import com.model.PacienteDTO;
import com.persistence.entities.Odontologo;
import com.persistence.entities.Paciente;
import com.persistence.repository.OdontologoRepository;
import com.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Optional<Paciente> guardar(Paciente p) {
		p.setFechaIngreso(new Date());
		return pacienteRepository.guardar(p);
	}
	
	public Optional<Paciente> buscar(Integer id) {
		return pacienteRepository.buscarPorId(id);
	}
	
	public Optional<List<Paciente>> buscarTodos() {
		return pacienteRepository.buscarTodos();
	}
	
	public void eliminar(Integer id) {
		pacienteRepository.eliminar(id);
	}
	
	public Optional<Paciente> actualizar(Paciente p) {
		return pacienteRepository.actualizar(p);
	}
}
