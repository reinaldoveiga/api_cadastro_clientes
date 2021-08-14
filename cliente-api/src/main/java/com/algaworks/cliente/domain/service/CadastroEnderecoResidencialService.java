package com.algaworks.cliente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cliente.domain.exception.EntidadeEmUsoException;
import com.algaworks.cliente.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.cliente.domain.model.Consumidor;
import com.algaworks.cliente.domain.model.EnderecoResidencial;
import com.algaworks.cliente.domain.repository.ConsumidorRepository;
import com.algaworks.cliente.domain.repository.EnderecoResidencialRepository;

@Service
public class CadastroEnderecoResidencialService {

	@Autowired
	private EnderecoResidencialRepository enderecoResidencialRepository;
	
	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	//public EnderecoResidencial salvar(EnderecoResidencial enderecoResidencial) {
		//Long consumidorId = enderecoResidencial.getConsumidor().getId();
		//Consumidor consumidor = consumidorRepository.buscar(consumidorId);
		
		//if(consumidor == null) {
		//	throw new EntidadeNaoEncontradaException(
			//		String.format("Não existe cadastro de consumidor com código %d", consumidorId));
		//}
		
		//enderecoResidencial.setConsumidor(consumidor);
		
		//return enderecoResidencialRepository.salvar(enderecoResidencial);
		
	//}
	
	public EnderecoResidencial salvar(EnderecoResidencial enderecoResidencial) {
			return enderecoResidencialRepository.salvar(enderecoResidencial);
		
		
	}

}
