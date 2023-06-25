package com.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
	
	public MapperConfig(){
		System.out.println("Instanciando el Mapper Config");
	}
	
	@Bean
	public ModelMapper getModelMapper(){
		System.out.println("Voy a instanciar el Model Mapper");
		return new ModelMapper();
	}
}
