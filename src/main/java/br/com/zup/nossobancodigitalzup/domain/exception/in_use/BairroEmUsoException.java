package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class BairroEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public BairroEmUsoException(Long bairroId) {
		super(String.format("Bairro de id %d não pode ser removido, pois está em uso.", bairroId));
	}
}
