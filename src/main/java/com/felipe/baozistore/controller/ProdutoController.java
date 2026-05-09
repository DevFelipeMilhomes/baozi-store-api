package com.felipe.baozistore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.baozistore.model.Produto;
import com.felipe.baozistore.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	private final ProdutoService service;
	
	ProdutoController(ProdutoService serviceProduto){
		this.service = serviceProduto;
	}
	
	@GetMapping
	public List<Produto> listarTodos(){
		return service.listarTodos();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> listarPorId(@PathVariable("id") Long id){
		try {
			Produto produto = service.listarPorId(id);
			return ResponseEntity.ok().body(produto);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Produto produto){
		try {
			Produto produtoNovo = service.criar(produto);
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoNovo);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
		try {
			Produto produto = service.editar(id, produtoAtualizado);
			return ResponseEntity.ok(produto);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Boolean result = service.deletarPorId(id);
		if(result) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().body("Produto não encontrado");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
