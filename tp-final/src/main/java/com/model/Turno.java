package com.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table
public class Turno {
	@Id
	@SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
	private Integer id;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "odontologo_id")
	private Odontologo odontologo;
	private LocalDate date;
	
	public Turno() {
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
