package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoIdInput {
	
	@NotNull
	private Long idEndereco;
	
	@NotBlank
	private String clienteCpfCnpj;
}
