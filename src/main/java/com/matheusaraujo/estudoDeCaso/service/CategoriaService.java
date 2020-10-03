package com.matheusaraujo.estudoDeCaso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	//Injetando o repositório
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	//Busca uma categoria
	public Categoria Find(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.get();
	}
}
