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

import com.felipe.baozistore.model.Cliente;
import com.felipe.baozistore.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private final ClienteService service;
	
	ClienteController(ClienteService clienteServ){
		this.service = clienteServ;
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Cliente clienteRequest){
		try {
			Cliente cliente = service.criar(clienteRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
			
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public List<Cliente> listarTodos(){
		return service.listarTodos();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> listarPorId(@PathVariable("id") Long id) {
		try {
			Cliente cliente = service.listarPorId(id);
			return ResponseEntity.ok().body(cliente);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody Cliente cliente){
		try {
			Cliente clienteAtualizado = service.editar(id, cliente);
			return ResponseEntity.ok(clienteAtualizado);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(cliente);
		}
			
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id){
		Boolean result = service.deletarPorId(id);
		if(result) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().body("Cliente não encontrado");
	}	
	
	
	
	
}
