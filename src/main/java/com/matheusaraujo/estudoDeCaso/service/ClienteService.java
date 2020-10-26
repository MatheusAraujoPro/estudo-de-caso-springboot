package com.matheusaraujo.estudoDeCaso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.repository.ClienteRepository;
import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Injetando o repositório
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//Busca uma Cliente
	public Cliente Find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(!cliente.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ " " + "Tipo :" +Cliente.class.getName());
		}
		return cliente.get();
	}
}
