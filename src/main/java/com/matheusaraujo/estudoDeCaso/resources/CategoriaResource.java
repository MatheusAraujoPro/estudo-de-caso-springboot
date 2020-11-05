package com.matheusaraujo.estudoDeCaso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusaraujo.estudoDeCaso.DTO.CategoriaDTO;
import com.matheusaraujo.estudoDeCaso.domain.Categoria;
import com.matheusaraujo.estudoDeCaso.service.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	//Método Listar
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Categoria> Find(@PathVariable Integer id) {
		
			Categoria categoria = categoriaService.Find(id);
			return ResponseEntity.ok(categoria);
	}
	
	//Método Inserir
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(@Valid @RequestBody CategoriaDTO categoriaDto){
		Categoria categoria = categoriaService.fromDto(categoriaDto);
		categoria = categoriaService.Insert(categoria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getId()).toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	//Método Atualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> Update(@Valid @RequestBody CategoriaDTO categoriaDto, @PathVariable Integer id){
		Categoria categoria = categoriaService.fromDto(categoriaDto);
		categoria.setId(id);
		categoria = categoriaService.Update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	//Método Deletar
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> Delete(@PathVariable Integer id){
		categoriaService.Delete(id);
		return ResponseEntity.noContent().build();		
	}
	
	//Método Listar todos
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> FindAll() {		
			List<Categoria> categorias = categoriaService.FindAll();
			List<CategoriaDTO> categoriasDTOs = categorias.stream().
					map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
			return ResponseEntity.ok(categoriasDTOs);
	}
	
	//Método Listar com Paginação
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> FindPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "6") Integer linePerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {	
		
			Page<Categoria> categorias = categoriaService.FindPage(page, linePerPage, orderBy, direction);
			Page<CategoriaDTO> categoriasDTOs = categorias.map(cat -> new CategoriaDTO(cat));
			return ResponseEntity.ok(categoriasDTOs);
	}
	
}
