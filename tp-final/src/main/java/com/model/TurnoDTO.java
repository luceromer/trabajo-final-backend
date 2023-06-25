package com.model;

import java.time.LocalDate;

public class TurnoDTO {
	private Long id;
	private PacienteDTO pacienteDTO;
	private OdontologoDTO odontologoDTO;
	private LocalDate date;
	
	public TurnoDTO() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public PacienteDTO getPaciente() {
		return pacienteDTO;
	}
	
	public void setPaciente(PacienteDTO pacienteDTO) {
		this.pacienteDTO = pacienteDTO;
	}
	
	public OdontologoDTO getOdontologo() {
		return odontologoDTO;
	}
	
	public void setOdontologo(OdontologoDTO odontologoDTO) {
		this.odontologoDTO = odontologoDTO;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "TurnoDTO{" +
				"id=" + id +
				", paciente=" + pacienteDTO +
				", odontologo=" + odontologoDTO +
				", date=" + date +
				'}';
	}
}
