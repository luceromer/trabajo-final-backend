package com.service;
import com.model.TurnoDTO;
import com.persistence.entities.Turno;
import com.persistence.repository.TurnoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
	private TurnoRepository turnoRepository;
	public TurnoService() {
		this.turnoRepository = turnoRepository;
	}
	
	public Optional<Turno> registrarTurno(Turno turno){
		Optional<Turno> respuesta = null;
		if(turno.getDate().isAfter(LocalDate.now())){
			respuesta = turnoRepository.guardar(turno);
		}
		
		return respuesta;
	}
	public Optional<List<Turno>> listar(){
		return turnoRepository.buscarTodos();
	}
	public void eliminar(Integer id){
		turnoRepository.eliminar(id);
	}
	public Optional<Turno> actualizar(Turno turno){
		return turnoRepository.actualizar(turno);
	}
	public Optional<Turno> buscar(Integer id){
		return turnoRepository.buscarPorId(id);
	}
	
}
