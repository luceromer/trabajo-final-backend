package com.persistence.repository;

import com.model.TurnoDTO;
import com.persistence.entities.Paciente;
import com.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
	
	@Query(value = "SELECT * FROM Turnos WHERE paciente_id = ?1", nativeQuery = true)
	Set<Turno> buscarTurnoPorPaciente(Long id);
	
}
