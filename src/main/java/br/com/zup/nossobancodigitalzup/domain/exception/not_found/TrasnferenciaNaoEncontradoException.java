package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class TrasnferenciaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public TrasnferenciaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public TrasnferenciaNaoEncontradoException(Long idTrasnferencia) {
		this(String.format("Não existe um registro de uma trasnferencia com o id %d", idTrasnferencia));
	}
	
}
