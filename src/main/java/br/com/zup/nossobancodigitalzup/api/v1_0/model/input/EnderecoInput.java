package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoInput {

	@NotBlank
	private String cep;
	
	@NotBlank
	private String complemento;
	
	@NotNull
	private Integer numero;
	
	@NotBlank
	private String rua;
	
	@Valid
	@NotNull
	private BairroIdInput bairro;
}
