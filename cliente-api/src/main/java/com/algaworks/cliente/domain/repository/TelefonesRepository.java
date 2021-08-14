package com.algaworks.cliente.domain.repository;

import java.util.List;

import com.algaworks.cliente.domain.model.Telefones;

public interface TelefonesRepository {
	
	List<Telefones> listar();
	Telefones buscar(Long id);
	Telefones salvar(Telefones telefone);
	void remover(Telefones telefone);
}
