package com.algaworks.cliente.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.cliente.domain.exception.EntidadeEmUsoException;
import com.algaworks.cliente.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.cliente.domain.exception.EntidadeNaoInformadaException;
import com.algaworks.cliente.domain.model.Consumidor;
import com.algaworks.cliente.domain.repository.ConsumidorRepository;
import com.algaworks.cliente.domain.service.CadastroConsumidorService;

@RestController
@RequestMapping(value = "/consumidores")
public class ConsumidorController {

	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	@Autowired
	private CadastroConsumidorService cadastroConsumidor;
	
	@GetMapping
	public List<Consumidor> listar() {
		return consumidorRepository.listar();
	}
	
	@GetMapping(value = "/{consumidorId}")
	public ResponseEntity<Consumidor> buscar(@PathVariable Long id) {
		Consumidor consumidor = consumidorRepository.buscar(id);
		
		if(consumidor != null) {
			return ResponseEntity.ok(consumidor);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Consumidor consumidor) {	
		//Consumidor consumidorAtual = null;
		try {
			Consumidor consumidorAtual = cadastroConsumidor.salvar(consumidor);
			return ResponseEntity.status(HttpStatus.CREATED).body(consumidorAtual);
		
		}catch(EntidadeNaoInformadaException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{consumidorId}")
	public ResponseEntity<Consumidor> atualizar(@PathVariable Long consumidorId, @RequestBody Consumidor consumidor) {
		Consumidor consumidorAtual = consumidorRepository.buscar(consumidorId);
		
		if(consumidorAtual != null) {
			BeanUtils.copyProperties(consumidor, consumidorAtual, "id");
			
			consumidorAtual = consumidorRepository.atualizar(consumidorAtual);
			return ResponseEntity.ok(consumidorAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{consumidorId}")
	public ResponseEntity<?> remover(@PathVariable Long consumidorId) {
		
		try {
			cadastroConsumidor.excluir(consumidorId);
			Consumidor consumidor = consumidorRepository.buscar(consumidorId);
			return ResponseEntity.ok(consumidor);
			//return ResponseEntity.status(HttpStatus.LOCKED).build();
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
	
}
