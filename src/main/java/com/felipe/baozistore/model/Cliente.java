package com.felipe.baozistore.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(columnDefinition="DATE")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate clienteDesde;
	
	// Getters e Setters;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getClienteDesde() {
		return clienteDesde;
	}
	public void setClienteDesde(LocalDate clienteDesde) {
		this.clienteDesde = clienteDesde;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clienteDesde, id, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(clienteDesde, other.clienteDesde) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", clienteDesde=" + clienteDesde + "]";
	}
}
