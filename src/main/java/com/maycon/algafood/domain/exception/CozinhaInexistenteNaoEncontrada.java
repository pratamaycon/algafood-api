package com.maycon.algafood.domain.exception;

public class CozinhaInexistenteNaoEncontrada extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public CozinhaInexistenteNaoEncontrada(String msg) {
		super(msg);
	}
}
