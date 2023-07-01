package com.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Pacientes")
public class Paciente {
	
	@Id
	@SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
	private Long id;
	private String nombre;
	private String apellido;
	private String dni;
	private Date fechaIngreso;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "domicilio_id")
	private Domicilio domicilio;
	
	@OneToMany(mappedBy = "paciente")
	@JsonIgnore
	private Set<Turno> listaTurnos;
	
	public Paciente() {
	}
	
	public Set<Turno> getListaTurnos() {
		return listaTurnos;
	}
	
	public void addTurnoToListaDeTurnos(Turno turno) {
		listaTurnos.add(turno);
	}
	
	public void deleteTurnoFromListaDeTurnos(Turno turno) {
		listaTurnos.remove(turno);
	}
	
	public Paciente(Long id, String nombre, String apellido, String dni, Date fechaIngreso, Domicilio domicilio) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilio = domicilio;
	}
	
	public Paciente(String nombre, String apellido, String dni, Date fechaIngreso, Domicilio domicilio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilio = domicilio;
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
	
	public Domicilio getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	@Override
	public String toString() {
		return "Paciente{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", dni='" + dni + '\'' +
				", fechaIngreso=" + fechaIngreso +
				", domicilio=" + domicilio +
				'}';
	}
	
}
