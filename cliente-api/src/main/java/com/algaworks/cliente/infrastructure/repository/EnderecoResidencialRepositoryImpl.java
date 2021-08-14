package com.algaworks.cliente.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.cliente.domain.model.EnderecoResidencial;
import com.algaworks.cliente.domain.repository.EnderecoResidencialRepository;

@Component
public class EnderecoResidencialRepositoryImpl implements EnderecoResidencialRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<EnderecoResidencial> listar() {
		return manager.createQuery("from EnderecoResidencial", EnderecoResidencial.class)
				.getResultList();
	}

	@Override
	public EnderecoResidencial buscar(Long id) {
		return manager.find(EnderecoResidencial.class, id);
	}
	
	@Transactional
	@Override
	public EnderecoResidencial salvar(EnderecoResidencial enderecoResidencial) {
		return manager.merge(enderecoResidencial);
	}
	
	@Transactional
	@Override
	public void remover(EnderecoResidencial enderecoResidencial) {
		enderecoResidencial = buscar(enderecoResidencial.getId());
		manager.remove(enderecoResidencial);
	}

	
}
