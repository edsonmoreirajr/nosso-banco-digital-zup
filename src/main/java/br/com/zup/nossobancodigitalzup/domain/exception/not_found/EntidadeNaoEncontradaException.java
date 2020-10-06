package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;

public abstract class EntidadeNaoEncontradaException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
