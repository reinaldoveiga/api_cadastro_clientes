package com.algaworks.cliente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.cliente.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.cliente.domain.model.Consumidor;
import com.algaworks.cliente.domain.model.Telefones;
import com.algaworks.cliente.domain.repository.ConsumidorRepository;
import com.algaworks.cliente.domain.repository.TelefonesRepository;

@Service
public class CadastroTelefonesService {
	
	@Autowired
	private TelefonesRepository telefonesRepository;
	
	//@Autowired
	//private ConsumidorRepository consumidorRepository;
	
	public Telefones salvar(Telefones telefone) {
		//Long consumidorId = telefone.getConsumidor().getId();
		//Consumidor consumidor = consumidorRepository.buscar(consumidorId);
		
		//if(consumidor == null) {
			//throw new EntidadeNaoEncontradaException(
				//	String.format("Não existe cadastro de consumidor com código %d", consumidorId));
		//}
		
		//telefone.setConsumidor(consumidor);
		return telefonesRepository.salvar(telefone);
		
	}
}
