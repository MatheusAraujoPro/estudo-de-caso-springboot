package com.matheusaraujo.estudoDeCaso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.domain.Cidade;
import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.domain.Endereco;
import com.matheusaraujo.estudoDeCaso.domain.Estado;
import com.matheusaraujo.estudoDeCaso.domain.ItemPedido;
import com.matheusaraujo.estudoDeCaso.domain.Pagamento;
import com.matheusaraujo.estudoDeCaso.domain.PagamentoComBoleto;
import com.matheusaraujo.estudoDeCaso.domain.PagamentoComCartao;
import com.matheusaraujo.estudoDeCaso.domain.Pedido;
import com.matheusaraujo.estudoDeCaso.domain.Produto;
import com.matheusaraujo.estudoDeCaso.domain.enuns.EstadoPagamento;
import com.matheusaraujo.estudoDeCaso.domain.enuns.TipoCliente;
import com.matheusaraujo.estudoDeCaso.repository.CategoriaRepository;
import com.matheusaraujo.estudoDeCaso.repository.CidadeRepository;
import com.matheusaraujo.estudoDeCaso.repository.ClienteRepository;
import com.matheusaraujo.estudoDeCaso.repository.EnderecoRepository;
import com.matheusaraujo.estudoDeCaso.repository.EstadoRepository;
import com.matheusaraujo.estudoDeCaso.repository.ItemPedidoRepository;
import com.matheusaraujo.estudoDeCaso.repository.PagamentoRepository;
import com.matheusaraujo.estudoDeCaso.repository.PedidoRepository;
import com.matheusaraujo.estudoDeCaso.repository.ProdutoRepository;


@SpringBootApplication
public class EstudoDeCasoApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clieneRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EstudoDeCasoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Populando o banco de dados
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat4 = new Categoria(null, "Cama mesa e banho");
		Categoria cat5 = new Categoria(null, "Eletro eletrônicos");
		Categoria cat6 = new Categoria(null, "Vestuário");
		Categoria cat7 = new Categoria(null, "Higiene pessoal");
		Categoria cat8 = new Categoria(null, "Bebidas Lácteas");
		Categoria cat9 = new Categoria(null, "Bebidas Alcoólicas");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Toalha de mesa", 60.00);
		Produto p5 = new Produto(null, "Perfume de rosas", 80.00);
		Produto p6 = new Produto(null, "TV", 180.00);
		Produto p7 = new Produto(null, "Whisky", 80.00);
		Produto p8 = new Produto(null, "Leite", 80.00);
		Produto p9 = new Produto(null, "Calcinha", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat4.getProdutos().addAll(Arrays.asList(p4));
		cat5.getProdutos().addAll(Arrays.asList(p6));
		cat6.getProdutos().addAll(Arrays.asList(p9));
		cat7.getProdutos().addAll(Arrays.asList(p5));
		cat9.getProdutos().addAll(Arrays.asList(p8));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat4));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p6.getCategorias().addAll(Arrays.asList(cat5));
		p5.getCategorias().addAll(Arrays.asList(cat7));
		p8.getCategorias().addAll(Arrays.asList(cat9));
		
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat4, cat5, cat6, cat7, cat8, cat9));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clieneRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p1, 0.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1, ip3));
		p2.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
