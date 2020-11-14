package com.matheusaraujo.estudoDeCaso.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusaraujo.estudoDeCaso.DTO.ClienteDTO;
import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Cliente> Find(@PathVariable Integer id) {
		
			Cliente cliente = clienteService.Find(id);
			return ResponseEntity.ok(cliente);
	}
	
	//Método Atualizar
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> Update(@Valid @RequestBody ClienteDTO ClienteDto, @PathVariable Integer id){
			Cliente Cliente = clienteService.fromDto(ClienteDto);
			Cliente.setId(id);
			Cliente = clienteService.Update(Cliente);
			return ResponseEntity.noContent().build();
		}
		
		//Método Deletar
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> Delete(@PathVariable Integer id){
			clienteService.Delete(id);
			return ResponseEntity.noContent().build();		
		}
		
		//Método Listar todos
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> FindAll() {		
				List<Cliente> Clientes = clienteService.FindAll();
				List<ClienteDTO> ClientesDTOs = Clientes.stream().
						map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
				return ResponseEntity.ok(ClientesDTOs);
		}
		
		//Método Listar com Paginação
		@RequestMapping(value = "/page", method = RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> FindPage(
				@RequestParam(value = "page", defaultValue = "0") Integer page, 
				@RequestParam(value = "linePerPage", defaultValue = "6") Integer linePerPage, 
				@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
				@RequestParam(value = "direction", defaultValue = "ASC") String direction) {	
			
				Page<Cliente> Clientes = clienteService.FindPage(page, linePerPage, orderBy, direction);
				Page<ClienteDTO> ClientesDTOs = Clientes.map(cliente -> new ClienteDTO(cliente));
				return ResponseEntity.ok(ClientesDTOs);
		}
		
}
