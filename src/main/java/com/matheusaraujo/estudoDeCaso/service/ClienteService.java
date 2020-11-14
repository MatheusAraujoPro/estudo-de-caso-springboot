package com.matheusaraujo.estudoDeCaso.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheusaraujo.estudoDeCaso.DTO.ClienteDTO;
import com.matheusaraujo.estudoDeCaso.DTO.ClienteNewDTO;
import com.matheusaraujo.estudoDeCaso.domain.Cidade;
import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.domain.Endereco;
import com.matheusaraujo.estudoDeCaso.domain.enuns.TipoCliente;
import com.matheusaraujo.estudoDeCaso.repository.ClienteRepository;
import com.matheusaraujo.estudoDeCaso.repository.EnderecoRepository;
import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Injetando o repositório
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
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
	
	public Cliente fromDto(@Valid ClienteNewDTO clienteNewDto) {
		Cliente cliente = new Cliente(null, clienteNewDto.getNome(), clienteNewDto.getEmail(),
				clienteNewDto.getCpfOucnpj(), TipoCliente.ToEnum(clienteNewDto.getTipo()));
		
		Endereco endereco = new Endereco(null, clienteNewDto.getLogradouro(),clienteNewDto.getNumero(),
				clienteNewDto.getComplemento(), clienteNewDto.getBairro(), clienteNewDto.getCep(),
				cliente, new Cidade(clienteNewDto.getIdCidade(), null, null));
		
		cliente.getTelefones().add(clienteNewDto.getTelefone1());
		
		if(clienteNewDto.getTelefone2() != null)
			cliente.getTelefones().add(clienteNewDto.getTelefone2());
		
		if(clienteNewDto.getTelefone3() != null)
			cliente.getTelefones().add(clienteNewDto.getTelefone3());
		
		
		cliente.getEnderecos().add(endereco);		
		
		return cliente;
	}
	
	public Cliente fromDto(@Valid ClienteDTO clienteDTO) {
		return new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());	
		newCliente.setEmail(cliente.getEmail());
	}
	
	@Transactional
	public Cliente Insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
}

