package br.com.zup.nossobancodigitalzup.domain.exception.not_found;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EnderecoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EnderecoNaoEncontradoException(Long idEndereco) {
		this(String.format("Não existe um registro de um endereço com o id %d", idEndereco));
	}
	
}
