package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String cpfCnpj) {
		super(String.format("Não existe um registro de um usuario com o cpf ou cnpj %s", cpfCnpj));
	}
	
}
