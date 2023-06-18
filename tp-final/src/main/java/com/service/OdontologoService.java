package com.service;

import com.model.Odontologo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
	public class OdontologoService {
		private IDao<Odontologo> odontologoDao;
		Logger logger = Logger getLogger(OdontologoService.class);
		
		public OdontologoService() {
			odontologoDao = new OdontologoDaoH2();
			logger.info("Se ha creado una instancia");
		}
		
		public OdontologoService(IDao<Odontologo> odontologoDao) {
			this.odontologoDao = odontologoDao;
			logger.info("Se ha creado una instancia");
		}
		
		public Odontologo registrarOdontologo(Odontologo odontologo) {
			return odontologoDao.guardar(odontologo);
		}
		
		public void eliminar(Integer id) {
			odontologoDao.eliminar(id);
		}
		
		public Optional<Odontologo> buscar(Integer id) {
			return odontologoDao.buscar(id);
		}
		
		public List<Odontologo> buscarTodos() {
			return odontologoDao.buscarTodos();
		}
		
		public Odontologo actualizar(Odontologo odontologo) {
			return odontologoDao.actualizar(odontologo);
		}
	}
	

