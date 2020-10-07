package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class PropostaEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public PropostaEmUsoException(Long propostaId) {
		super(String.format("Proposta de id %d não pode ser removido, pois está em uso.", propostaId));
	}
}
