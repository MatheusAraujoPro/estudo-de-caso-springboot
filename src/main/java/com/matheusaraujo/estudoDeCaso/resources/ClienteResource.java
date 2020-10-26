package com.matheusaraujo.estudoDeCaso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
