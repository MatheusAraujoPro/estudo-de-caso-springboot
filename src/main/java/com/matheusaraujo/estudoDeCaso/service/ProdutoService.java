package com.matheusaraujo.estudoDeCaso.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.domain.Produto;
import com.matheusaraujo.estudoDeCaso.repository.CategoriaRepository;
import com.matheusaraujo.estudoDeCaso.repository.ProdutoRepository;
import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository ;
	
	public Produto Find(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if(!produto.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ " " + "Tipo :" +Categoria.class.getName());
		}	
		
		return produto.get();		
	}
	
	public Page<Produto> Search(String nome, List<Integer> ids, 
		Integer page, Integer linePerPage, String orderBy, String direction){	
		
		PageRequest pageRequest = PageRequest.of(page, linePerPage, 
				Sort.Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}
