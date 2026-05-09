package com.felipe.baozistore.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.felipe.baozistore.model.Produto;
import com.felipe.baozistore.repository.ProdutoRepository;

@Service
public class ProdutoService {
	private final ProdutoRepository repository;
	
	ProdutoService(ProdutoRepository produtoRepo){
		this.repository = produtoRepo;
	}
	
	public Produto criar(Produto produto) {
		if(produto.getNome() == null || produto.getNome().isEmpty()) {
			throw new RuntimeException("O nome do Produto é obrigatório.");
		}
		if(produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0){
			throw new RuntimeException("O preço deve ser maior que zero");
		}
		if(produto.getEstoque()==null) {
			produto.setEstoque(true);
		}
		
		return repository.save(produto);
	}
	
	public List<Produto> listarTodos(){
		return repository.findAll();
	}
	
	public Produto listarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado"));
	}
	
	public Boolean deletarPorId(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Produto editar(Long id, Produto produtoAtualizado) {
		Produto produto = listarPorId(id);
		
		produto.setNome(produtoAtualizado.getNome());
		produto.setEstoque(produtoAtualizado.getEstoque());
		produto.setPreco(produtoAtualizado.getPreco());
		
		return repository.save(produto);
	}
}
