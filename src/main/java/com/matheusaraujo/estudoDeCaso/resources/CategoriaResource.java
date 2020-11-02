package com.matheusaraujo.estudoDeCaso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Categoria> Find(@PathVariable Integer id) {
		
			Categoria categoria = categoriaService.Find(id);
			return ResponseEntity.ok(categoria);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(@RequestBody Categoria categoria){
		categoria = categoriaService.Insert(categoria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getId()).toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> Update(@RequestBody Categoria categoria, @PathVariable Integer id){
		categoria.setId(id);
		categoria = categoriaService.Update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> Delete(@PathVariable Integer id){
		categoriaService.Delete(id);
		return ResponseEntity.noContent().build();		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> FindAll() {		
			List<Categoria> categorias = categoriaService.FindAll();
			List<CategoriaDTO> categoriasDTOs = categorias.stream().
					map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
			return ResponseEntity.ok(categoriasDTOs);
	}
}
