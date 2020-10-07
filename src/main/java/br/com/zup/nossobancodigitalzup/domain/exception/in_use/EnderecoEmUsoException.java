package br.com.zup.nossobancodigitalzup.domain.exception.in_use;

public class EnderecoEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EnderecoEmUsoException(Long enderecoId) {
		super(String.format("Endereco de id %d não pode ser removido, pois está em uso.", enderecoId));
	}
}
