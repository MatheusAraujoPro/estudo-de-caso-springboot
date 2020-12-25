package com.matheusaraujo.estudoDeCaso.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusaraujo.estudoDeCaso.domain.Pedido;
import com.matheusaraujo.estudoDeCaso.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> Find(@PathVariable Integer id){
		
		Pedido pedido = pedidoService.Find(id);
		return ResponseEntity.ok(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(@Valid @RequestBody Pedido pedido){
		
		pedido = pedidoService.Insert(pedido);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pedido.getId()).toUri(); 
		
		return ResponseEntity.created(uri).build();
	}

}
