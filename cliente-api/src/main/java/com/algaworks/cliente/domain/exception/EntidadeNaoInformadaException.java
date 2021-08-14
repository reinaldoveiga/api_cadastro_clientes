package com.algaworks.cliente.domain.exception;

public class EntidadeNaoInformadaException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public EntidadeNaoInformadaException(String mensagem) {
		super(mensagem);
	}
}
