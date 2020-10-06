package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferenciaInput {

	@NotBlank
	private String agencia;
	
	@NotBlank
	private String banco;
	
	@NotBlank
	private String conta;
	
	@NotBlank
	private String cpfCnpj;
	
	@NotNull
	private Date dataTransferencia;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private byte favoritado;
	
	@NotBlank
	private String tipoConta;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal valor;
	
	@Valid
	@NotNull
	private ContaIdInput contaId;
	
}
