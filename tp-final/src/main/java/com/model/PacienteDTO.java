package com.model;

import java.util.Date;
import java.util.Set;

public class PacienteDTO {
	

	private Long id;
	private String nombre;
	private String apellido;
	private String dni;
	private Date fechaIngreso;
	private DomicilioDTO domicilioDTO;
	
	private Set<OdontologoDTO> listaOdontologos;
	
	public DomicilioDTO getDomicilioDTO() {
		return domicilioDTO;
	}
	
	public void setDomicilioDTO(DomicilioDTO domicilioDTO) {
		this.domicilioDTO = domicilioDTO;
	}
	
	public Set<OdontologoDTO> getListaOdontologos() {
		return listaOdontologos;
	}
	
	public void setListaOdontologos(Set<OdontologoDTO> listaOdontologos) {
		this.listaOdontologos = listaOdontologos;
	}
	
	public PacienteDTO() {
	}
	
	public PacienteDTO(Long id, String nombre, String apellido, String dni, Date fechaIngreso, DomicilioDTO domicilioDTO) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilioDTO = domicilioDTO;
	}
	
	public PacienteDTO(String nombre, String apellido, String dni, Date fechaIngreso, DomicilioDTO domicilioDTO) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilioDTO = domicilioDTO;
	}
	
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public DomicilioDTO getDomicilio() {
		return domicilioDTO;
	}
	
	public void setDomicilio(DomicilioDTO domicilioDTO) {
		this.domicilioDTO = domicilioDTO;
	}
	
	@Override
	public String toString() {
		return "PacienteDTO{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", dni='" + dni + '\'' +
				", fechaIngreso=" + fechaIngreso +
				", domicilio=" + domicilioDTO +
				'}';
	}
	
}
