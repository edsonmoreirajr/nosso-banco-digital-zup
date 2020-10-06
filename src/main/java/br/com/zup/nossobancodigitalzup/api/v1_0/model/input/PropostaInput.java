package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PropostaInput {

	@NotNull
	private Date dataProposta;
	
	@NotBlank
	private String status;
	
}
