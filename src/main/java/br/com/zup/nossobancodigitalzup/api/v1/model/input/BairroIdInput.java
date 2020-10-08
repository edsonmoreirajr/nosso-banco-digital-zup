package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BairroIdInput {

	@NotNull
	private Long bairroId;
}
