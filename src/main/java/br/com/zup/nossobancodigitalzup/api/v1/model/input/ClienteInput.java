package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.nossobancodigitalzup.core.validation.Cnh;
import br.com.zup.nossobancodigitalzup.core.validation.DataNascimento;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {

	@NotNull
	@Valid
	ClienteIdInput clienteId;
	
	@NotNull
	private byte ativo;
	
	@NotBlank
	@Cnh
	private String cnh;
	
	@NotNull
	@DataNascimento
	private Date dataNascimento;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
}

