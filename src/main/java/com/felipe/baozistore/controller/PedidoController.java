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

import com.felipe.baozistore.model.Pedido;
import com.felipe.baozistore.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	private final PedidoService service;
	
	PedidoController(PedidoService pedidoService){
		this.service = pedidoService;
	}
	
	@GetMapping
	public List<Pedido> listarTodos(){
		return service.listarTodos();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> listarPorId(@PathVariable("id") Long id){
		try {
			Pedido pedido = service.listarPorId(id);
			return ResponseEntity.ok().body(pedido);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Pedido pedido){
		try {
			Pedido pedidoNovo = service.criar(pedido);
			return ResponseEntity.status(HttpStatus.CREATED).body(pedidoNovo);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody Pedido pedidoAtualizado){
		try {
			Pedido pedido = service.editar(id, pedidoAtualizado);
			return ResponseEntity.ok(pedido);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Boolean result = service.deletarPorId(id);
		if(result) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().body("Produto não encontrado");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
