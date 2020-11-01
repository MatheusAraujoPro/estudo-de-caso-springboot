package com.matheusaraujo.estudoDeCaso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
