package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class PropostaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PropostaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PropostaNaoEncontradaException(Long idProposta) {
		this(String.format("Não existe um registro de uma proposta com o id %d", idProposta));
	}
	
}
