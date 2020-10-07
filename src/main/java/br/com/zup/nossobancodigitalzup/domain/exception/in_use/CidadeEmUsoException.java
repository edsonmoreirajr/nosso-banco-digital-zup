package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class CidadeEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public CidadeEmUsoException(Long cidadeId) {
		super(String.format("Cidade de id %d não pode ser removido, pois está em uso.", cidadeId));
	}
}
