package com.algaworks.cliente.domain.repository;

import java.util.List;

import com.algaworks.cliente.domain.model.EnderecoResidencial;

public interface EnderecoResidencialRepository {
	
	List<EnderecoResidencial> listar();
	EnderecoResidencial buscar(Long id);
	EnderecoResidencial salvar(EnderecoResidencial enderecoResidencial);
	void remover(EnderecoResidencial enderecoResidencial);
}
