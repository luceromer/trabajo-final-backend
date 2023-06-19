package com.persistence.repository;

import com.model.DomicilioDTO;
import com.persistence.entities.Domicilio;
import com.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DomicilioRepository extends JpaRepository<DomicilioDTO, Long> {
	
	@Query("SELECT d FROM Domicilio d where d.id = ?1")
	Optional<Domicilio> buscarDomicilio(Integer id);
	
	Optional<List<Domicilio>> buscarTodos();
	
	Optional<Domicilio> guardar(Domicilio domicilio);
	
	Optional<Domicilio> actualizar(Domicilio domicilio);
	
	Optional<Domicilio> eliminar(Integer id);
	
}
