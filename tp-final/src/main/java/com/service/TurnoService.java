package com.service;
import com.model.Turno;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
	private IDao<Turno> turnoRepository;
	
	public TurnoService(IDao<Turno> turnoRepository) {
		this.turnoRepository = turnoRepository;
	}
	
	public Turno registrarTurno(Turno turno){
		Turno respuesta = null;
		if(turno.getDate().isAfter(LocalDate.now())){
			respuesta = turnoRepository.guardar(turno);
		}
		
		return respuesta;
	}
	public List<Turno> listar(){
		return turnoRepository.buscarTodos();
	}
	public void eliminar(Integer id){
		turnoRepository.eliminar(id);
	}
	public Turno actualizar(Turno turno){
		return turnoRepository.actualizar(turno);
	}
	public Optional<Turno> buscar(Integer id){
		return turnoRepository.buscar(id);
	}
	
}
