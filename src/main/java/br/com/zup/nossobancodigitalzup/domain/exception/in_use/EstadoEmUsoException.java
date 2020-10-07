package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class EstadoEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EstadoEmUsoException(Long estadoId) {
		super(String.format("Estado de id %d não pode ser removido, pois está em uso.", estadoId));
	}
}
