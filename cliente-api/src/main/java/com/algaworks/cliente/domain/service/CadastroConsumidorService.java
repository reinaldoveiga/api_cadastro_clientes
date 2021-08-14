package com.algaworks.cliente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.cliente.domain.exception.EntidadeEmUsoException;
import com.algaworks.cliente.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.cliente.domain.exception.EntidadeNaoInformadaException;
import com.algaworks.cliente.domain.model.Consumidor;
import com.algaworks.cliente.domain.repository.ConsumidorRepository;

@Service
public class CadastroConsumidorService {
	
	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	public Consumidor salvar(Consumidor consumidor) {
		try {
			
			if(consumidor.getEnderecoResidencial() == null && consumidor.getTelefones() == null) {
				throw new EntidadeNaoInformadaException(
				
						String.format("Campos necessários para o cadastro não foram informados. Verifique os campos endereço e telefone."));
			
			}else if(consumidor.getEnderecoResidencial() == null) {
				throw new EntidadeNaoInformadaException(
						String.format("Campos necessários para o cadastro não foram informados. Verifique o campo endereço."));
			
			
			}else if(consumidor.getTelefones() == null) {
				throw new EntidadeNaoInformadaException(
						String.format("Campos necessários para o cadastro não foram informados. Verifique o campo telefone."));
			}
				
			
			return consumidorRepository.salvar(consumidor);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Consumidor não pode ser cadastrado, pois o Cpf/Cnpj informado já está em uso."));
		}
		
	}
	
	public Consumidor atualizar(Consumidor consumidor) {
		return consumidorRepository.atualizar(consumidor);
	}
	
	public void excluir(Long consumidorId) {
		try {
			consumidorRepository.remover(consumidorId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de consumidor com código %d", consumidorId));
		
		}
	}
}
