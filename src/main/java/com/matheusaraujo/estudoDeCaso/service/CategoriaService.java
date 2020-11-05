package com.matheusaraujo.estudoDeCaso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.DTO.CategoriaDTO;
import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.repository.CategoriaRepository;
import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {
	
	//Injetando o repositório
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	//Busca uma categoria
	public Categoria Find(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(!categoria.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ " " + "Tipo :" +Categoria.class.getName());
		}
		return categoria.get();
	}


	public Categoria Insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}


	public Categoria Update(Categoria categoria) {		
		Find(categoria.getId());
		return categoriaRepository.save(categoria);
	}

	public void Delete(Integer id) {
		Find(id);
		try { 
			categoriaRepository.deleteById(id);	
		}catch(DataIntegrityViolationException e) {
			throw new com.matheusaraujo
			.estudoDeCaso.service
			.exceptions.DataIntegrityViolationException("Não é possível excluir uma categoria que tem produtos");
		}
	}


	public List<Categoria> FindAll() {		
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> FindPage(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}
}
