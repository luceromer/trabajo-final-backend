package com.persistence.repository;

import com.model.TurnoDTO;
import com.persistence.entities.Paciente;
import com.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoDTO, Long> {
	
	@Query("SELECT t FROM Turno t where t.id = ?1")
	Optional<Turno> buscarPorId(Integer id);
	
	Optional<List<Turno>> buscarTodos();
	
	Optional<Turno> guardar(Turno turno);
	
	Optional<Turno> actualizar(Turno turno);
	
	Optional<Turno> eliminar(Integer id);
	
}
