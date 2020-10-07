package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;

public class EntidadeEmUsoException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
	
}
