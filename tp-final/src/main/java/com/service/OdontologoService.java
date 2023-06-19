package com.service;

import com.model.OdontologoDTO;
import com.persistence.entities.Odontologo;
import com.persistence.repository.OdontologoRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
	public class OdontologoService {
	
	@Autowired
	private OdontologoRepository odontologoRepository;
	//	Logger logger = Logger.getLogger(OdontologoService.class);
	
	public OdontologoService()// {
	{
	
	}
	//logger.info("Se ha creado una instancia");
	//	}
		
		
		public Optional<Odontologo> registrarOdontologo(Odontologo odontologo) {
			return odontologoRepository.guardar(odontologo);
		}
		
		public void eliminar(Integer id) {
			odontologoRepository.eliminar(id);
		}
		
		public Optional<Odontologo> buscar(Integer id) {
			return odontologoRepository.buscarPorId(id);
		}
		
		public Optional<List<Odontologo>> buscarTodos() {
			return odontologoRepository.buscarTodos();
		}
		
		public Optional<Odontologo> actualizar(Odontologo odontologo) {
			return odontologoRepository.
					actualizar(odontologo);
		}
	}
	

