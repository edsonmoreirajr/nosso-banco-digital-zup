package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import br.com.zup.nossobancodigitalzup.core.validation.groups.ClienteGroupSequenceProvider;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaFisicaCpf;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaJuridicaCnpj;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class ClienteIdInput {

	@NotBlank
	@CPF(groups = PessoaFisicaCpf.class)
	@CNPJ(groups = PessoaJuridicaCnpj.class)
	private String cpfCnpj;
	
}
