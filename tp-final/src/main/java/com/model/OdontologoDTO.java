package com.model;

import java.util.Set;

public class OdontologoDTO {
	
		private Long id;
		private Integer numeroMatricula;
		private String nombre;
		private String apellido;
		
		private Set<PacienteDTO> listaDePacientes;
	
	
	public OdontologoDTO() {
	}
	
	public OdontologoDTO(Long id, Integer numeroMatricula, String nombre, String apellido) {
			this.id = id;
			this.numeroMatricula = numeroMatricula;
			this.nombre = nombre;
			this.apellido = apellido;
		}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public Integer getNumeroMatricula() {
			return numeroMatricula;
		}
		
		public void setNumeroMatricula(Integer numeroMatricula) {
			this.numeroMatricula = numeroMatricula;
		}
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getApellido() {
			return apellido;
		}
		
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
	
	@Override
	public String toString() {
		return "OdontologoDTO{" +
				"id=" + id +
				", numeroMatricula=" + numeroMatricula +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				'}';
	}
	
	public Set<PacienteDTO> getListaDePacientes() {
		return listaDePacientes;
	}
	
	public void setListaDePacientes(Set<PacienteDTO> listaDePacientes) {
		this.listaDePacientes = listaDePacientes;
	}
}
