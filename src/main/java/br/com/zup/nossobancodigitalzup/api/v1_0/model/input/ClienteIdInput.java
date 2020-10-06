package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import javax.validation.constraints.NotBlank;

public class ClienteIdInput {

	@NotBlank
	private String cpfCnpj;
}
