package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class ClienteEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public ClienteEmUsoException(String cpfCnpj) {
		super(String.format("Cliente de cpf ou cnpj %s não pode ser removido, pois está em uso.", cpfCnpj));
	}
}
