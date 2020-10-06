package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradoException(Long idCidade) {
		this(String.format("Não existe um registro de uma cidade com id %d", idCidade));
	}
	
}
