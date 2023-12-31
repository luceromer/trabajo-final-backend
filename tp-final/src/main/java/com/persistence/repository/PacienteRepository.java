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
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
