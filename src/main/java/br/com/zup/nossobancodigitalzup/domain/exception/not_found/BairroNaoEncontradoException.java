package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class BairroNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public BairroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public BairroNaoEncontradoException(Long idBairro) {
		this(String.format("Não existe um registro de um bairro com o id %d", idBairro));
	}
	
}
