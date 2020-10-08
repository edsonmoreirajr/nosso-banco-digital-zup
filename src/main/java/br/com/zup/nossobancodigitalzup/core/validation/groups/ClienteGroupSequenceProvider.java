package br.com.zup.nossobancodigitalzup.core.validation.groups;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.zup.nossobancodigitalzup.api.v1.model.ClienteModel;
import br.com.zup.nossobancodigitalzup.api.v1.model.input.ClienteInput;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaFisicaCpf;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaJuridicaCnpj;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<ClienteInput> {

	@Override
	public List<Class<?>> getValidationGroups(ClienteInput clienteInput) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(ClienteModel.class);

		if (clienteInput != null) {
			if (clienteInput.getPessoaJuridica()) {
				groups.add(PessoaJuridicaCnpj.class);
			} else {
				groups.add(PessoaFisicaCpf.class);
			}
		}
		
		
		return groups;
	}

}
