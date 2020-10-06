package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class PropostaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PropostaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PropostaNaoEncontradoException(Long idProposta) {
		this(String.format("Não existe um registro de uma proposta com o id %d", idProposta));
	}
	
}
