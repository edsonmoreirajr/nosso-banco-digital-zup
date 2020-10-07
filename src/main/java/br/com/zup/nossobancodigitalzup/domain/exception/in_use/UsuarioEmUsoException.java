package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class UsuarioEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public UsuarioEmUsoException(String usuarioId) {
		super(String.format("Usuário de id %s não pode ser removido, pois está em uso.", usuarioId));
	}
}
