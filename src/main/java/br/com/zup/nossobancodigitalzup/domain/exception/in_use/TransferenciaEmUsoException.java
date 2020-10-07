package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class TransferenciaEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public TransferenciaEmUsoException(Long transferenciaId) {
		super(String.format("Transferência de id %d não pode ser removido, pois está em uso.", transferenciaId));
	}
}
