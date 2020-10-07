package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class ContaEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public ContaEmUsoException(Long contaId) {
		super(String.format("Conta de id %d não pode ser removido, pois está em uso.", contaId));
	}
}
