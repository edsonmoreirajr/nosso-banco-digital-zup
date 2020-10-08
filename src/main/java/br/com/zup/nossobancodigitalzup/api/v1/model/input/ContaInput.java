package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaInput {

	@NotBlank
	private String agencia;
	
	@NotBlank
	private String codigoBanco;
	
	@NotBlank
	private String conta;

	@NotBlank
	private String tipoConta;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal saldo;
	
	@Valid
	@NotNull
	private PropostaIdInput proposta;
}
