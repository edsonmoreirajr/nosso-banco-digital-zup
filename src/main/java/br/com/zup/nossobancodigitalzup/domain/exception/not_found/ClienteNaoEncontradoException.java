package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String cpfCnpj) {
		super(String.format("Não existe um registro de um cliente com o cpf ou cnpj %s", cpfCnpj));
	}
	
}
