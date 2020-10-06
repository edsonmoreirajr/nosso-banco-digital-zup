package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PropostaInput {

	@NotNull
	private Date dataProposta;
	
	@NotBlank
	private String status;
	
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
}
