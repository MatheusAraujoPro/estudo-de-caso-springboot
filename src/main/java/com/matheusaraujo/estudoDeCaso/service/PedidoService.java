package com.matheusaraujo.estudoDeCaso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.domain.Pedido;
import com.matheusaraujo.estudoDeCaso.repository.PedidoRepository;
import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido Find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if(!pedido.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ " " + "Tipo :" +Categoria.class.getName());
		}	
		
		return pedido.get();		
	}

}
