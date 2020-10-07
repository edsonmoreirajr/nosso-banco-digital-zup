package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class TransferenciaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public TransferenciaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public TransferenciaNaoEncontradaException(Long idTrasnferencia) {
		this(String.format("Não existe um registro de uma trasnferência com o id %d", idTrasnferencia));
	}
	
}
