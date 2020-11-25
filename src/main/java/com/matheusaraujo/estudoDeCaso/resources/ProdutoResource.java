package com.matheusaraujo.estudoDeCaso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusaraujo.estudoDeCaso.DTO.ProdutoDTO;
import com.matheusaraujo.estudoDeCaso.domain.Produto;
import com.matheusaraujo.estudoDeCaso.resources.utils.URL;
import com.matheusaraujo.estudoDeCaso.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> Find(@PathVariable Integer id){
		
		Produto Produto = produtoService.Find(id);
		return ResponseEntity.ok(Produto);
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> FindPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "6") Integer linePerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction ){
		
			String nomeDecoded = URL.decodeParam(nome);
			List<Integer> ids = URL.decodeUrlIntList(categorias);
			
			Page<Produto> pageProduto = produtoService.Search(nomeDecoded, ids , 
					page, linePerPage, orderBy, direction);
			
			Page<ProdutoDTO> pageProdutoDTO = pageProduto.map(prod -> new ProdutoDTO(prod));		
		
		return ResponseEntity.ok(pageProdutoDTO);
	}
}
