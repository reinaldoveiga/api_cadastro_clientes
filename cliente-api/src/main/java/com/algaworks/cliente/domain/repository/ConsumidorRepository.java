package com.algaworks.cliente.domain.repository;

import java.util.List;

import com.algaworks.cliente.domain.model.Consumidor;

public interface ConsumidorRepository {
	
	List<Consumidor> listar();
	Consumidor buscar(Long id);
	Consumidor atualizar(Consumidor consumidor);
    Consumidor salvar(Consumidor consumidor);
    void remover(Long id);
}
