package com.maycon.algafood.domain.exception;

public class EstadoInexistenteNaoEncontrada extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EstadoInexistenteNaoEncontrada(String msg) {
		super(msg);
	}
	
}
