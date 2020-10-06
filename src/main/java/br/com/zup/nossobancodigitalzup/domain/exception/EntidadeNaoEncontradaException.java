package br.com.zup.nossobancodigitalzup.domain.exception;

public abstract class EntidadeNaoEncontradaException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
