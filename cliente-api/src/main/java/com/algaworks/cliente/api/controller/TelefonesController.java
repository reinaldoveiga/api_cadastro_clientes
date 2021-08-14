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
import com.algaworks.cliente.domain.model.Telefones;
import com.algaworks.cliente.domain.repository.TelefonesRepository;
import com.algaworks.cliente.domain.service.CadastroTelefonesService;

@RestController
@RequestMapping(value = "/telefones")
public class TelefonesController {
	
	@Autowired
	private TelefonesRepository telefonesRepository;
	
	@Autowired
	private CadastroTelefonesService cadastroTelefones;
	
	@GetMapping
	public List<Telefones> listar() {
		return telefonesRepository.listar();
	}
	
	@GetMapping(value = "/{enderecoResidencialId}")
	public ResponseEntity<Telefones> buscar(@PathVariable Long telefonesId) {
		Telefones telefones = telefonesRepository.buscar(telefonesId);
		
		if(telefones != null) {
			return ResponseEntity.ok(telefones);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Telefones telefones ) {
		try {
			telefones = cadastroTelefones.salvar(telefones);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(telefones);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{enderecoResidencialId}")
	public ResponseEntity<?> atualizar(@PathVariable Long telefoneId,
			@RequestBody Telefones telefone) {
		try {
			Telefones telefoneAtual = telefonesRepository.buscar(telefoneId);
			
			if (telefoneAtual != null) {
				BeanUtils.copyProperties(telefone, telefoneAtual, "id");
				
				telefoneAtual = cadastroTelefones.salvar(telefoneAtual);
				return ResponseEntity.ok(telefoneAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
}
