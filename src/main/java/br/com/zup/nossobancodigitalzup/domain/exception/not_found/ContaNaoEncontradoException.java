package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class ContaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ContaNaoEncontradoException(Long idConta) {
		this(String.format("Não existe um registro de uma conta com o id %d", idConta));
	}
	
}
