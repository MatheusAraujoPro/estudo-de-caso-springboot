package com.matheusaraujo.estudoDeCaso.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.domain.ItemPedido;
import com.matheusaraujo.estudoDeCaso.domain.PagamentoComBoleto;
import com.matheusaraujo.estudoDeCaso.domain.Pedido;
import com.matheusaraujo.estudoDeCaso.domain.enuns.EstadoPagamento;
import com.matheusaraujo.estudoDeCaso.repository.ItemPedidoRepository;
import com.matheusaraujo.estudoDeCaso.repository.PagamentoRepository;
import com.matheusaraujo.estudoDeCaso.repository.PedidoRepository;

import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;	
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	
	public Pedido Find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if(!pedido.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ " " + "Tipo :" +Categoria.class.getName());
		}	
		
		return pedido.get();		
	}
	
	@Transactional
	public Pedido Insert(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setCliente(clienteService.Find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.setInstante(new Date());
		pedido.getPagamento().setPedido(pedido);
		
		//Gerando a data de vencimento
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto boleto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(boleto, pedido.getInstante());
		}
		
		//salvando pedido
		pedido = pedidoRepository.save(pedido);
		
		//salvando pagamento
		pagamentoRepository.save(pedido.getPagamento());
		
		//salvando itens de pedido
		for(ItemPedido item: pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.Find(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
				
		return pedido;
	}

}
