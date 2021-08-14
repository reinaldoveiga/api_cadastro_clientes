package com.algaworks.cliente.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.cliente.domain.model.Consumidor;
import com.algaworks.cliente.domain.model.Status;
import com.algaworks.cliente.domain.repository.ConsumidorRepository;

@Component
public class ConsumidorRepositoryImpl implements ConsumidorRepository {
	
	@PersistenceContext
    private EntityManager manager;
	
	@Override
	public List<Consumidor> listar() {
		  return manager.createQuery("from Consumidor", Consumidor.class)
	                .getResultList();
	}
	
	@Override
	public Consumidor buscar(Long id) {
		return manager.find(Consumidor.class, id);
	}
	
	@Transactional
    @Override
    public Consumidor salvar(Consumidor consumidor) {
		String consumidores = consumidor.getCpfCnpj();
		List<Consumidor> listaConsumidores = listar();
		
		for(Consumidor c : listaConsumidores) {
			if(c.getCpfCnpj().equals(consumidores)) {
				
				throw new DataIntegrityViolationException(consumidores);
				
			}
			
		}	
		
		return manager.merge(consumidor);
    }
	
	@Transactional
	@Override
	public Consumidor atualizar(Consumidor consumidor) {
		return manager.merge(consumidor);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Consumidor consumidor  = buscar(id);
		
		if(consumidor == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		consumidor.setStatus(Status.CANCELADO);
		
		manager.merge(consumidor);
	}

}
