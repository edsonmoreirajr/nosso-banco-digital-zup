package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.model.input.BairroInput;
import br.com.zup.nossobancodigitalzup.domain.model.Bairro;
import br.com.zup.nossobancodigitalzup.domain.model.Cidade;

@Component
public class BairroInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Bairro toDomainObject(BairroInput bairroInput) {
		return modelMapper.map(bairroInput, Bairro.class);
	}
	
	public void copyToDomainObject(BairroInput bairroInput, Bairro bairro) {
		bairro.setCidade(new Cidade());
		modelMapper.map(bairroInput, bairro);
	}
}
