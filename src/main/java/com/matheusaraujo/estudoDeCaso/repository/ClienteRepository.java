package com.matheusaraujo.estudoDeCaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.matheusaraujo.estudoDeCaso.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	//Usando padrões de nomes
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
