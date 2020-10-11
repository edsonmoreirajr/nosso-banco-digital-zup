package br.com.zup.nossobancodigitalzup.domain.exception.duplicity;

public class CpfCnpjDuplicadoException extends EntidadeDuplicadaException {

	private static final long serialVersionUID = 1L;

	public CpfCnpjDuplicadoException(String cpfCnpj) {
		super(String.format("Já existe um cliente cadastrado com o cpf ou cnpj %s", cpfCnpj));
	}
}
