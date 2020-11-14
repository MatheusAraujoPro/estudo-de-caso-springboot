package com.matheusaraujo.estudoDeCaso.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.matheusaraujo.estudoDeCaso.DTO.ClienteDTO;

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


	public Cliente Update(Cliente cliente) {		
		Cliente newCliente = Find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}

	public void Delete(Integer id) {
		Find(id);
		try { 
			clienteRepository.deleteById(id);	
		}catch(DataIntegrityViolationException e) {
			throw new com.matheusaraujo
			.estudoDeCaso.service
			.exceptions.DataIntegrityViolationException("Não é possível excluir, pois há entidades relacionadas");
		}
	}


	public List<Cliente> FindAll() {		
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> FindPage(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());	
		newCliente.setEmail(cliente.getEmail());
	}
}

