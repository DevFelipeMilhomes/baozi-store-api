package com.felipe.baozistore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felipe.baozistore.model.Pedido;
import com.felipe.baozistore.model.Produto;
import com.felipe.baozistore.repository.ClienteRepository;
import com.felipe.baozistore.repository.PedidoRepository;
import com.felipe.baozistore.repository.ProdutoRepository;

@Service
public class PedidoService {
	private final PedidoRepository repositoryPedido;
	private final ClienteRepository repositoryCliente;
	private final ProdutoRepository repositoryProduto;
	
	PedidoService(PedidoRepository pedidoRepo, ClienteRepository clienteRepo, ProdutoRepository produtoRepo){
		this.repositoryPedido = pedidoRepo;
		this.repositoryCliente = clienteRepo;
		this.repositoryProduto = produtoRepo;
	}
	
	public Pedido criar(Pedido pedido) {
		if (!repositoryCliente.existsById(pedido.getClienteId())) {
	        throw new RuntimeException("Cliente não encontrado.");
	    }

	    Produto produto = repositoryProduto.findById(pedido.getProdutoId())
	        .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

	    if (produto.getEstoque() == null || !produto.getEstoque()) {
	        throw new RuntimeException("Produto sem estoque.");
	    }

	    if (pedido.getQuantidade() == null || pedido.getQuantidade() <= 0) {
	        throw new RuntimeException("A quantidade do pedido deve ser pelo menos 1.");
	    }

	    return repositoryPedido.save(pedido);
	}
	
	public List<Pedido> listarTodos(){
		return repositoryPedido.findAll();
	}
	
	public Pedido listarPorId(Long id) {
		return repositoryPedido.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido com ID: " + id + " não encontrado"));
	}
	
	public Boolean deletarPorId(Long id) {
		if(repositoryPedido.existsById(id)) {
			repositoryPedido.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public Pedido editar(Long id, Pedido pedidoAtualizado) {
		Pedido pedido = listarPorId(id);
		
		pedido.setClienteId(pedidoAtualizado.getClienteId());
		pedido.setProdutoId(pedidoAtualizado.getProdutoId());
		pedido.setQuantidade(pedidoAtualizado.getQuantidade());
		
		return repositoryPedido.save(pedido);
	}
	
	
	
	
	
	
}
