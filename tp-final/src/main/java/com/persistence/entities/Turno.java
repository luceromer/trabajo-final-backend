package com.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Turnos")
public class Turno {
	@Id
	@SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "odontologo_id", nullable = false)
	private Odontologo odontologo;
	private LocalDate date;
	
	public Turno() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Odontologo getOdontologo() {
		return odontologo;
	}
	
	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Turno{" +
				"id=" + id +
				", paciente=" + paciente +
				", odontologo=" + odontologo +
				", date=" + date +
				'}';
	}
}
