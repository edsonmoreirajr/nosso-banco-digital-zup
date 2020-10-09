package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.nossobancodigitalzup.core.validation.Cep;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInput {

	@NotBlank
	@Cep
	private String cep;
	
	@NotBlank
	private String complemento;
	
	@NotNull
	private Integer numero;
	
	@NotBlank
	private String rua;
	
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	@Valid
	@NotNull
	private BairroIdInput bairro;
}
