package br.com.zup.nossobancodigitalzup.domain.exception.duplicity;

public class CpfCnpjEmailDuplicadosException extends EntidadeDuplicadaException {

	private static final long serialVersionUID = 1L;

	public CpfCnpjEmailDuplicadosException(String cpfCnpj,String email) {
		super(String.format("Já existe um cliente cadastrado com o cpf ou cnpj %s e email %s", cpfCnpj, email));
	}
}
