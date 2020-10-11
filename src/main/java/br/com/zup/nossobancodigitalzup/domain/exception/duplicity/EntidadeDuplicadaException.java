package br.com.zup.nossobancodigitalzup.domain.exception.duplicity;

import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;

public abstract class EntidadeDuplicadaException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntidadeDuplicadaException(String mensagem) {
		super(mensagem);
	}
	
}
