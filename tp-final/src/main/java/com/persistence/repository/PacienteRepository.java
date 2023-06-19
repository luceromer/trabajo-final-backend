package com.persistence.repository;

import com.model.PacienteDTO;
import com.persistence.entities.Domicilio;
import com.persistence.entities.Odontologo;
import com.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteDTO, Long> {
	@Query("SELECT p FROM Paciente p where p.id = ?1")
	Optional<Paciente> buscarPorId(Integer id);
	
	Optional<List<Paciente>> buscarTodos();
	
	Optional<Paciente> guardar(Paciente paciente);
	
	Optional<Paciente> actualizar(Paciente paciente);
	
	Optional<Paciente> eliminar(Integer id);
	
}
