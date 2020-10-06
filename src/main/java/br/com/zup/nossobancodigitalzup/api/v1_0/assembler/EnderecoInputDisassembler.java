package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.model.input.EnderecoInput;
import br.com.zup.nossobancodigitalzup.domain.model.Endereco;

@Component
public class EnderecoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Endereco toDomainObject(EnderecoInput enderecoInput) {
		return modelMapper.map(enderecoInput, Endereco.class);
	}
	
	public void copyToDomainObject(EnderecoInput enderecoInput, Endereco endereco) {
		modelMapper.map(enderecoInput, endereco);
	}
}
