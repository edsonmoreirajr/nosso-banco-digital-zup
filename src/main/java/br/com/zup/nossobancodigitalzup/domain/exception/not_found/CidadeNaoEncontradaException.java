package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long idCidade) {
		this(String.format("Não existe um registro de uma cidade com id %d", idCidade));
	}
	
}
