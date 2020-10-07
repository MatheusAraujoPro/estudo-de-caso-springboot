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
import com.matheusaraujo.estudoDeCaso.domain.Estado;
import com.matheusaraujo.estudoDeCaso.domain.Produto;
import com.matheusaraujo.estudoDeCaso.repository.CategoriaRepository;
import com.matheusaraujo.estudoDeCaso.repository.CidadeRepository;
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
		
		
	}

}
