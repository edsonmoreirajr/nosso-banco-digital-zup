package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {

	@NotNull
	private byte ativo;
	
	@NotBlank
	private String cnh;
	
	@NotNull
	private Date dataNascimento;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
}

