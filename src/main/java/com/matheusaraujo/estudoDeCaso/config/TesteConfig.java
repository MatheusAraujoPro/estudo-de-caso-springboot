package com.matheusaraujo.estudoDeCaso.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheusaraujo.estudoDeCaso.service.DbService;

@Configuration
@Profile("teste")
public class TesteConfig {
	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbService.instatiateDataBaseTest();
		return true;
	}
	

}
