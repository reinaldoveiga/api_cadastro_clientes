package com.algaworks.cliente.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.cliente.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.cliente.domain.model.EnderecoResidencial;
import com.algaworks.cliente.domain.repository.EnderecoResidencialRepository;
import com.algaworks.cliente.domain.service.CadastroEnderecoResidencialService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResidencialController {
	
	@Autowired
	private EnderecoResidencialRepository enderecoResidencialRepository;
	
	@Autowired
	private CadastroEnderecoResidencialService cadastroEnderecoResidencial;
	
	@GetMapping
	public List<EnderecoResidencial> listar() {
		return enderecoResidencialRepository.listar();
	}
	
	@GetMapping(value = "/{enderecoResidencialId}")
	public ResponseEntity<EnderecoResidencial> buscar(@PathVariable Long enderecoResidencialId) {
		EnderecoResidencial enderecoResidencial = enderecoResidencialRepository.buscar(enderecoResidencialId);
		
		if(enderecoResidencial != null) {
			return ResponseEntity.ok(enderecoResidencial);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody EnderecoResidencial enderecoResidencial ) {
		try {
			enderecoResidencial = cadastroEnderecoResidencial.salvar(enderecoResidencial);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(enderecoResidencial);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{enderecoResidencialId}")
	public ResponseEntity<?> atualizar(@PathVariable Long enderecoResidencialId,
			@RequestBody EnderecoResidencial enderecoResidencial) {
		try {
			EnderecoResidencial enderecoResidencialAtual = enderecoResidencialRepository.buscar(enderecoResidencialId);
			
			if (enderecoResidencialAtual != null) {
				BeanUtils.copyProperties(enderecoResidencial, enderecoResidencialAtual, "id");
				
				enderecoResidencialAtual = cadastroEnderecoResidencial.salvar(enderecoResidencialAtual);
				return ResponseEntity.ok(enderecoResidencialAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
}
