package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.model.input.PropostaInput;
import br.com.zup.nossobancodigitalzup.domain.model.Proposta;

@Component
public class PropostaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Proposta toDomainObject(PropostaInput propostaInput) {
		return modelMapper.map(propostaInput, Proposta.class);
	}
	
	public void copyToDomainObject(PropostaInput propostaInput, Proposta proposta) {
		modelMapper.map(propostaInput, proposta);
	}
}
