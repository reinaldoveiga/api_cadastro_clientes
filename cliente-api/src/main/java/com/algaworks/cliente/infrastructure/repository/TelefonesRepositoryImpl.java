package com.algaworks.cliente.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.cliente.domain.model.Telefones;
import com.algaworks.cliente.domain.repository.TelefonesRepository;

@Component
public class TelefonesRepositoryImpl implements TelefonesRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Telefones> listar() {
		return manager.createQuery("from Telefones", Telefones.class)
				.getResultList();
	}

	@Override
	public Telefones buscar(Long id) {
		return manager.find(Telefones.class, id);
	}
	
	@Transactional
	@Override
	public Telefones salvar(Telefones telefones) {
		return manager.merge(telefones);
	}
	
	@Transactional
	@Override
	public void remover(Telefones telefones) {
		telefones = buscar(telefones.getId());
		manager.remove(telefones);
	}


}
