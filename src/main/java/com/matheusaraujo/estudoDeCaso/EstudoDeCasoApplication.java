package com.matheusaraujo.estudoDeCaso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.domain.Cidade;
import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.domain.Endereco;
import com.matheusaraujo.estudoDeCaso.domain.Estado;
import com.matheusaraujo.estudoDeCaso.domain.Produto;
import com.matheusaraujo.estudoDeCaso.domain.enuns.TipoCliente;
import com.matheusaraujo.estudoDeCaso.repository.CategoriaRepository;
import com.matheusaraujo.estudoDeCaso.repository.CidadeRepository;
import com.matheusaraujo.estudoDeCaso.repository.ClienteRepository;
import com.matheusaraujo.estudoDeCaso.repository.EnderecoRepository;
import com.matheusaraujo.estudoDeCaso.repository.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(EstudoDeCasoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Populando o banco de dados
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório ");
		
		Produto p1 = new Produto(null, "Computador", 1000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Ceará");
		
		Cidade c1 = new Cidade(null, "Fortaleza", est2);
		Cidade c2 = new Cidade(null, "Fortim", est2);
		Cidade c3 = new Cidade(null, "Uberlândia", est1);
		
		Cliente cl1 = new Cliente(null, "Matheus", "matheus@email.com", "732427834", TipoCliente.PESSOA_FISICA);
		Cliente cl2 = new Cliente(null, "Wine", "wine@email.com", "732427834", TipoCliente.PESSOA_FISICA);
		
		cl1.getTelefones().addAll(Arrays.asList("85 99929-3058"));
		cl2.getTelefones().addAll(Arrays.asList("85 99929-3058"));
		
		Endereco e1 = new Endereco(null, "Av. Mozart Lucena", "476", "Altos", "Jardim Guanabara", 
				"600000", cl1, c1);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		List<Categoria> categorias = new ArrayList<>();
		List<Produto> produtos     = new ArrayList<>();
		
		categorias.addAll(Arrays.asList(cat1, cat2));
		produtos.addAll(Arrays.asList(p1,p2,p3));
		
		categoriaRepository.saveAll(categorias);
		produtoRepository.saveAll(produtos);
		
		est1.getCidades().addAll(Arrays.asList(c3));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		clieneRepository.saveAll(Arrays.asList(cl1, cl2));
		enderecoRepository.saveAll(Arrays.asList(e1));
		
		
	}

}
