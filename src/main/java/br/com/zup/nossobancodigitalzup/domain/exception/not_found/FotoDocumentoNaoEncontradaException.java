package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class FotoDocumentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoDocumentoNaoEncontradaException(String id) {
		super(String.format("Não existe um registro de uma foto do cpf ou cnpj %s", id));
	}
	
}
