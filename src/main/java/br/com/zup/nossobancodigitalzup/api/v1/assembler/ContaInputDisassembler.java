package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.model.input.ContaInput;
import br.com.zup.nossobancodigitalzup.domain.model.Conta;

@Component
public class ContaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Conta toDomainObject(ContaInput contaInput) {
		return modelMapper.map(contaInput, Conta.class);
	}
	
	public void copyToDomainObject(ContaInput contaInput, Conta conta) {
		modelMapper.map(contaInput, conta);
	}
}
