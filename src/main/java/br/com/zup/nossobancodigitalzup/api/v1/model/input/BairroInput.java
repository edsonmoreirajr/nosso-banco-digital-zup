package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BairroInput {

	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
