package com.felipe.baozistore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.felipe.baozistore.model.Cliente;
import com.felipe.baozistore.repository.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository repository;
	
	ClienteService(ClienteRepository clienteRepo) {
		this.repository = clienteRepo;
	}
	
	public Cliente criar(Cliente cliente) {
		if(cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new RuntimeException("O nome do cliente é obrigatório.");
		}
		if(cliente.getClienteDesde()==null) {
			cliente.setClienteDesde(LocalDate.now());
		}
		return repository.save(cliente);
	}
	
	public List<Cliente> listarTodos(){
		return repository.findAll();
	}
	
	public Cliente listarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado."));
	}
	
	public Boolean deletarPorId(Long id){
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		} 
		return false;
	}
	
	public Cliente editar(Long id, Cliente clienteAtualizado) {
		Cliente cliente = listarPorId(id);
		
		cliente.setNome(clienteAtualizado.getNome());
		
		return repository.save(cliente);
	}
	
}
