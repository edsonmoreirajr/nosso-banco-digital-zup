package br.com.zup.nossobancodigitalzup.domain.exception.duplicity;

public class EmailDuplicadoException extends EntidadeDuplicadaException {

	private static final long serialVersionUID = 1L;

	public EmailDuplicadoException(String email) {
		super(String.format("Já existe um cliente cadastrado com o e-mail %s", email));
	}
}
