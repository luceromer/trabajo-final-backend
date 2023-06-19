package com.persistence.repository;

import com.model.OdontologoDTO;
import com.persistence.entities.Domicilio;
import com.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<OdontologoDTO, Long> {
	
	@Query("SELECT o FROM Odontologo o where o.id = ?1")
	Optional<Odontologo> buscarPorId(Integer id);
	
	Optional<List<Odontologo>> buscarTodos();
	
	Optional<Odontologo> guardar(Odontologo odontologo);
	
	Optional<Odontologo> actualizar(Odontologo odontologo);
	
	Optional<Odontologo> eliminar(Integer id);
	
}
