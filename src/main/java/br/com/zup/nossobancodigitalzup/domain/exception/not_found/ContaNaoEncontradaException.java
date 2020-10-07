package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class ContaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public ContaNaoEncontradaException(Long idConta) {
		this(String.format("Não existe um registro de uma conta com o id %d", idConta));
	}
	
}
