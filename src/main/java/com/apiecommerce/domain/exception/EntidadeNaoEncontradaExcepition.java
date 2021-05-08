package com.apiecommerce.domain.exception;

public class EntidadeNaoEncontradaExcepition extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExcepition(String mensagem) {
		
		super(mensagem);
	}

}
