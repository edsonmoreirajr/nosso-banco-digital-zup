package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import javax.validation.constraints.NotNull;

public class TransferenciaIdInput {

	@NotNull
	private Long idTransferencia;
	
	@NotNull
	private Long contaIdConta;
}
