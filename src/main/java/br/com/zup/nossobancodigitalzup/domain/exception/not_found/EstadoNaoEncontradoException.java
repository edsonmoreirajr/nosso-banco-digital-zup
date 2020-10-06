package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long idEstado) {
		this(String.format("Não existe um registro de um estado com o id %d", idEstado));
	}
	
}
