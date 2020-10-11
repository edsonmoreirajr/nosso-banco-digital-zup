package br.com.zup.nossobancodigitalzup.core.validation.groups;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.zup.nossobancodigitalzup.api.v1.model.input.ClienteIdInput;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaFisicaCpf;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaJuridicaCnpj;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<ClienteIdInput> {

	@Override
	public List<Class<?>> getValidationGroups(ClienteIdInput clienteInput) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(ClienteIdInput.class);

		if (clienteInput != null && clienteInput.getCpfCnpj() != null) {
			int quantidadeAlgarismos =  clienteInput.getCpfCnpj().length();
			if (quantidadeAlgarismos > 11) {
				groups.add(PessoaJuridicaCnpj.class);
			} else {
				groups.add(PessoaFisicaCpf.class);
			}
		}
		return groups;
	}

}
